package com.itacademy.molchanova.task1;

import com.itacademy.molchanova.task1.model.Detail;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import lombok.Data;

@Data
public class Dump {

    private int countDetail;
    private long start;
    private long workTime;
    private Deque<Detail> details = new ArrayDeque<>();

    public Dump(int countDetail, long start, long workTime) {
        this.countDetail = countDetail;
        this.start = start;
        this.workTime = workTime;
        for (int i = 0; i < countDetail; i++) {
            details.add(Detail.values()[new Random().nextInt(Detail.values().length)]);
        }
    }

    public synchronized Detail get() {
        Detail detail = null;
        if (countDetail > 0) {
            detail = details.pollLast();
            countDetail--;
        }
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return detail;
    }

    public synchronized void put() {
        details.add(Detail.values()[new Random().nextInt(Detail.values().length)]);
        countDetail++;
        notify();
    }
}
