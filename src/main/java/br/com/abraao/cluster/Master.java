package br.com.abraao.cluster;

import br.com.abraao.domain.Pod;
import br.com.abraao.domain.Worker;
import br.com.abraao.scheduler.MultiMetricScheduler;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class Master {

    private final List<Worker> workers;
    private final List<Pod> notAllocated;
    private final MultiMetricScheduler scheduler;

    public void schedule(Pod pod) {
        Worker worker = scheduler.selectWorker(pod, workers);

        if (nonNull(worker)) {
            worker.allocate(pod);
            System.out.println("[MASTER] " + pod.getName() + " -> " + worker.getName());
        } else {
            System.out.println("[MASTER] No available worker for " + pod.getName());
            notAllocated.add(pod);
        }
    }
}
