package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.FastDfs;
import com.ykrenz.fastdfs.FastDfsClientBuilder;
import com.ykrenz.fastdfs.config.FastDfsConfiguration;
import com.ykrenz.fastdfs.monitor.FastDfsMonitor;
import com.ykrenz.fastdfs.monitor.StorageMonitor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(FastDfs.class)
@ConditionalOnProperty(name = FastDfsConstants.ENABLED, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public FastDfs fastDfs(FastDfsProperties properties) {
        Assert.isTrue(!CollectionUtils.isEmpty(properties.getTrackerServers()), "FastDFS trackerServers can't be empty.");
        FastDfsConfiguration configuration = new FastDfsConfiguration();
        configuration.setDefaultGroup(properties.getDefaultGroup());
        configuration.setHttp(properties.getHttp());
        configuration.setConnection(properties.getConnection());
        return new FastDfsClientBuilder().build(properties.getTrackerServers(), configuration);
    }


    @ConditionalOnProperty(name = FastDfsConstants.ENABLED_MONITOR, havingValue = "true")
    @ConditionalOnBean(FastDfs.class)
    @ConditionalOnMissingBean
    @Bean
    public FastDfsMonitor fastDfsMonitor(FastDfsProperties properties, FastDfs fastDfs) {
        return new StorageMonitor(fastDfs.getTrackerClient(), properties.getMonitor().getReservedStorageSpace());
    }

    @ConditionalOnBean(FastDfsMonitor.class)
    @Bean
    @ConditionalOnMissingBean
    public FastDfsMonitorTask fastDfsMonitorTask(FastDfsProperties properties, FastDfsMonitor fastDfsMonitor) {
        FastDfsMonitorTask fastDfsMonitorTask = new FastDfsMonitorTask();
        fastDfsMonitorTask.schedule(fastDfsMonitor, properties.getMonitor().getDelayMillis(),
                properties.getMonitor().getPeriodMillis(), TimeUnit.MILLISECONDS);
        return fastDfsMonitorTask;
    }
}
