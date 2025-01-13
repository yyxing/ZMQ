package com.devil.zmq.broker.lock;

public interface Lock {

    void lock();

    void unlock();
}
