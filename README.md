# Pod Scheduler Simulator

## Description

This project simulates a Kubernetes-inspired cluster scheduler.
A Master node receives Pods and allocates them to Worker nodes according to multiple resource metrics, including:

- CPU
- Memory
- Disk Space
- Network Latency

The goal is to demonstrate operating systems concepts such as:

- Resource allocation
- Scheduling algorithms
- Multithreading
- Producer-Consumer pattern

## Architecture

The simulation is composed of:

- Master Node
- Worker Nodes
- Pods
- Producer Thread
- Consumer Thread
- Shared Blocking Queue

        Pod Producer
            |
            v
        BlockingQueue<Pod>
            |
            v
        Scheduler Consumer
            |
            v
        Master
            |
            v
        MultiMetricScheduler
            |
            +--> Worker 1
            +--> Worker 2
            +--> Worker 3

## Components

### Pod

Represents an application workload.

Attributes:
- CPU requirement
- Memory requirement
- Disk requirement

### Worker

Represents a cluster node.

Attributes:
- Available CPU
- Available Memory
- Available Disk
- Network Latency

### Scheduler (MultiMetricScheduler)

Responsible for selecting the best Worker for each Pod.

## Running

Requirements:

- Java 17+
- Maven 3.9+

Compile:

```bash
mvn clean package
java -jar target/pod-scheduler-simulator.jar