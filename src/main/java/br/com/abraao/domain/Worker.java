package br.com.abraao.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Worker {

    private String name;
    private int availableCpu;
    private int availableMemory;
    private int availableDisk;
    private int latency;
    private List<Pod> allocatedPods = new ArrayList<>();

    public Worker(String name, int availableCpu, int availableMemory, int availableDisk, int latency){
        this.name = name;
        this.availableCpu = availableCpu;
        this.availableMemory = availableMemory;
        this.availableDisk = availableDisk;
        this.latency = latency;
    }

    public void allocate(Pod pod) {
        this.availableCpu -= pod.getCpuRequired();
        this.availableMemory -= pod.getMemoryRequired();
        this.availableDisk -= pod.getDiskRequired();
        this.allocatedPods.add(pod);
    }
}
