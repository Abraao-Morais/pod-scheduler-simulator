package br.com.abraao.util;

import br.com.abraao.domain.Pod;
import br.com.abraao.domain.Worker;

import java.util.List;

public class StatisticsPrinter {

    public static void printWorkers(List<Worker> workers){
        System.out.println("=====================================");
        System.out.println("         CLUSTER FINAL STATE");
        System.out.println("=====================================");

        for (Worker worker : workers) {
            System.out.println("\nWorker: " + worker.getName());
            System.out.println("CPU Available: " + worker.getAvailableCpu());
            System.out.println("Memory Available: " + worker.getAvailableMemory());
            System.out.println("Disk Available: " + worker.getAvailableDisk());
            System.out.println("Latency: " + worker.getLatency());
            System.out.println("Allocated Pods: " + worker.getAllocatedPods().size());
            System.out.println("Pods:");
            printPods(worker.getAllocatedPods());
            System.out.println("=====================================");
        }
    }

    public static void printNotAllocated(List<Pod> pods){
        System.out.println("=====================================");
        System.out.println("            NOT ALLOCATED");
        System.out.println("=====================================");
        System.out.println("Not Allocated Pods: " + pods.size());
        System.out.println("Pods:");
        printPods(pods);
        System.out.println("=====================================");
    }

    private static void printPods(List<Pod> pods){
        for (Pod pod : pods)
            System.out.printf("Name: %s CPU: %d Memory: %d Disk: %d \n",
                    pod.getName(), pod.getCpuRequired(), pod.getMemoryRequired(), pod.getDiskRequired());
    }
}
