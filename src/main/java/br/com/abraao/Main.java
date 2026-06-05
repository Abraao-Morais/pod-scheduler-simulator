package br.com.abraao;

import br.com.abraao.consumer.SchedulerConsumer;
import br.com.abraao.cluster.Master;
import br.com.abraao.domain.Pod;
import br.com.abraao.domain.Worker;
import br.com.abraao.producer.PodProducer;
import br.com.abraao.scheduler.MultiMetricScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static br.com.abraao.util.StatisticsPrinter.printNotAllocated;
import static br.com.abraao.util.StatisticsPrinter.printWorkers;

public class Main {
    public static void main(String[] args) throws Exception{
        List<Worker> workers = List.of(
                new Worker("worker-1",10,16,100,10),
                new Worker("worker-2",18,16,400,10),
                new Worker("worker-3",10,16,100,20)
        );

        BlockingQueue<Pod> queue = new LinkedBlockingQueue<>();
        List<Pod> notAllocated = new ArrayList<>();
        MultiMetricScheduler scheduler = new MultiMetricScheduler();

        Master master = new Master(workers, notAllocated, scheduler);

        Thread producer = new Thread(new PodProducer(queue));
        Thread consumer = new Thread(new SchedulerConsumer(queue, master));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        printWorkers(workers);
        printNotAllocated(notAllocated);
    }
}