package com.timed;

@Timed
public interface Doer {
    void doSth() throws InterruptedException;

    void doSthElse() throws InterruptedException;
}
