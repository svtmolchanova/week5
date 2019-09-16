package com.itacademy.molchanova.task1;

public class Simulation {

    public static void main(String[] args) {

        Dump dump = new Dump(20, System.currentTimeMillis(), 10000l);
        Manufactory manufactory = new Manufactory(dump);
        Servant servant1 = new Servant(dump);
        Servant servant2 = new Servant(dump);
        new Thread(manufactory).start();
        new Thread(servant1).start();
        new Thread(servant2).start();
    }
}
