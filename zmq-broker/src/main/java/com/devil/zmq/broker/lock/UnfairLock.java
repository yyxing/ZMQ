package com.devil.zmq.broker.lock;

import java.util.concurrent.locks.ReentrantLock;

public class UnfairLock implements Lock {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock() {
        lock.unlock();
    }
}
