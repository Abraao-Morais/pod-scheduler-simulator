package br.com.abraao.scheduler;

import br.com.abraao.domain.Pod;
import br.com.abraao.domain.Worker;

import java.util.List;

public class MultiMetricScheduler {

    public Worker selectWorker(Pod pod, List<Worker> workers) {
        Worker bestWorker = null;
        double bestScore = -1;

        for (Worker worker : workers) {
            if (!canFit(pod, worker))
                continue;

            double score = calculateScore(pod, worker);

            if (score > bestScore) {
                bestScore = score;
                bestWorker = worker;
            }
        }

        return bestWorker;
    }

    private boolean canFit(Pod pod, Worker worker) {
        return worker.getAvailableCpu() >= pod.getCpuRequired()
                && worker.getAvailableMemory() >= pod.getMemoryRequired()
                && worker.getAvailableDisk() >= pod.getDiskRequired();
    }

    private double calculateScore(Pod pod, Worker worker) {
        double cpuScore = worker.getAvailableCpu() * 1.0;
        double memScore = worker.getAvailableMemory() * 1.0;
        double diskScore = worker.getAvailableDisk() * 0.8;

        double latencyPenalty = worker.getLatency() * 2.0;

        return cpuScore + memScore + diskScore - latencyPenalty;
    }
}
