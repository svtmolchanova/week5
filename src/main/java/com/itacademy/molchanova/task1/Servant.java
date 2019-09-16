package com.itacademy.molchanova.task1;

import com.itacademy.molchanova.task1.model.Detail;
import static java.lang.Thread.currentThread;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.Data;

@Data
public class Servant implements Runnable {

    private List<Queue<Detail>> listDetail = new ArrayList<>();
    Dump dump;

    public Servant(Dump dump) {
        this.dump = dump;
    }

    public void run() {
        while (System.currentTimeMillis() - dump.getStart() <= dump.getWorkTime()) {
            List<Detail> details = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Detail detail = dump.get();
                if (Objects.nonNull(detail)) {
                    details.add(detail);
                }
            }
            for (Detail d : details) {
                boolean stored = false;
                if (listDetail.isEmpty()) {
                    Queue<Detail> queue = new LinkedList<>();
                    queue.offer(d);
                    listDetail.add(queue);
                } else {
                    for (int j = 0; j < listDetail.size(); j++) {
                        if (!listDetail.get(j).contains(d)) {
                            listDetail.get(j).offer(d);
                            stored = true;
                            break;
                        }
                    }
                    if (stored == false) {
                        Queue<Detail> queue = new LinkedList<>();
                        queue.offer(d);
                        listDetail.add(queue);
                    }
                }
            }
        }
        amountRobot();
    }

    public Integer amountRobot() {
        int amountRobot = 0;
        for (Queue q : listDetail) {
            if (q.size() == Detail.values().length) {
                amountRobot++;
            }
        }
        System.out.println("Servant from " + currentThread().getName() + ": amount of robots " + amountRobot);
        return amountRobot;
    }

}
