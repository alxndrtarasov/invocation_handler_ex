package com.timed;

@Timed
public class SimpleDoer implements Doer {

    @Override
    public void doSth() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Override
    public void doSthElse() throws InterruptedException {
        Thread.sleep(2000);
    }
}
