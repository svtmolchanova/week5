package com.itacademy.molchanova.task1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Manufactory implements Runnable {

    Dump dump;

    public void run() {
        while (!Thread.interrupted()) {
            int random = (int) (Math.random() * 3 + 1);
            for (int i = 0; i < random; i++) {
                dump.put();
            }
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
