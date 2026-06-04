package br.com.abraao.domain;

import java.util.List;

public class Worker {

    private String name;
    private int availableCpu;
    private int availableMemory;
    private int availableDisk;
    private int latency;
    private List<Pod> allocatedPods;
}
