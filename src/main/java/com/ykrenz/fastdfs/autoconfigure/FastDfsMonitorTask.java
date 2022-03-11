package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.monitor.FastDfsMonitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FastDfsMonitorTask {

    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    public void schedule(FastDfsMonitor fastDfsMonitor,
                         long initialDelay,
                         long period,
                         TimeUnit unit) {
        service.scheduleAtFixedRate(fastDfsMonitor::monitor, initialDelay, period, unit);
    }

    public void shutdown() {
        service.shutdown();
    }
}
