package br.com.abraao.consumer;

import br.com.abraao.cluster.Master;
import br.com.abraao.domain.Pod;
import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class SchedulerConsumer implements Runnable{

    private final BlockingQueue<Pod> queue;
    private final Master master;

    private static final String STOP_CONDITION = "END";

    @Override
    public void run() {
        try {
            while (true) {
                Pod pod = queue.take();
                if (STOP_CONDITION.equalsIgnoreCase(pod.getName()))
                    break;

                System.out.println("[CONSUMER] Takes " + pod.getName());
                master.schedule(pod);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
