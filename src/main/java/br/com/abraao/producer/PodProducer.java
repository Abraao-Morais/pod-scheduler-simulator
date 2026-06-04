package br.com.abraao.producer;

import br.com.abraao.domain.Pod;
import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class PodProducer implements Runnable{

    private final BlockingQueue<Pod> queue;

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 20; i++) {
                Pod pod = new Pod("pod-" + i, random(1, 2), random(1, 3), random(1, 2));

                queue.put(pod);
                System.out.printf("[PRODUCER] Created %s Queue Size: %d\n",pod.getName(), queue.size());
                Thread.sleep(300);
            }
            queue.put(new Pod("END", 0, 0, 0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int random(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}
