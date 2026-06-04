package br.com.abraao.consumer;

import br.com.abraao.domain.Pod;
import br.com.abraao.domain.Worker;
import br.com.abraao.scheduler.MultiMetricScheduler;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class SchedulerConsumer implements Runnable{

    private final BlockingQueue<Pod> queue;
    private final List<Worker> workers;
    private final List<Pod> notAllocated;
    private final MultiMetricScheduler scheduler;

    private static final String STOP_CONDITION = "END";

    @Override
    public void run() {
        try {
            while (true) {
                Pod pod = queue.take();
                if (STOP_CONDITION.equalsIgnoreCase(pod.getName()))
                    break;

                Worker worker = scheduler.selectWorker(pod, workers);

                if (nonNull(worker)) {
                    worker.allocate(pod);
                    System.out.println("[SCHEDULER] " + pod.getName() + " -> " + worker.getName());
                } else {
                    System.out.println("[FAILED] " + pod.getName() + " could not be allocated");
                    notAllocated.add(pod);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
