package com.devil.zmq.broker.utils;

import java.util.concurrent.*;

public class ThreadPoolUtil {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8), r -> {
        Thread thread = new Thread(r);
        thread.setName("zmq-refresh-config-thread");
        return thread;
    });

    private static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = new Thread(r);
        thread.setName("zmq-refresh-config-thread");
        return thread;
    });

    public static void setPoolCoreSize(int size) {
        executor.setCorePoolSize(size);
    }

    public static void setMaxPoolSize(int size) {
        executor.setMaximumPoolSize(size);
    }

    public static void addTask(Runnable r) {
        executor.execute(r);
    }

    public static void addScheduledTask(Runnable r, int delay, TimeUnit unit) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(r, delay, delay, unit);
    }
}
