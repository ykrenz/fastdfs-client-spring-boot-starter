package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.FastDfs;
import com.ykrenz.fastdfs.FastDfsClientBuilder;
import com.ykrenz.fastdfs.config.FastDfsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(FastDfs.class)
@ConditionalOnProperty(name = FastDfsConstants.ENABLED, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(FastDfsProperties.class)
public class FastDfsAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsAutoConfiguration.class);


    @ConditionalOnMissingBean
    @Bean
    public FastDfs fastDfs(FastDfsProperties properties) {
        Assert.isTrue(!CollectionUtils.isEmpty(properties.getTrackerServers()), "FastDFS trackerServers can't be empty.");
        FastDfsConfiguration configuration = new FastDfsConfiguration();
        configuration.setDefaultGroup(properties.getDefaultGroup());
        configuration.setHttp(properties.getHttp());
        configuration.setConnection(properties.getConnection());
        FastDfs fastDfs = new FastDfsClientBuilder().build(properties.getTrackerServers(), configuration);
        LOGGER.info("fastDfs Client init...{}", properties.getTrackerServers());
        return fastDfs;
    }

//
//    @ConditionalOnProperty(name = FastDfsConstants.ENABLED_MONITOR, havingValue = "true")
//    @ConditionalOnBean(FastDfs.class)
//    @ConditionalOnMissingBean
//    @Bean
//    public FastDfsMonitor fastDfsMonitor(FastDfsProperties properties, FastDfs fastDfs) {
//        return new StorageMonitor(fastDfs.getTrackerClient(), properties.getMonitor().getReservedStorageSpace());
//    }

//    @ConditionalOnBean(FastDfsMonitor.class)
//    @Bean
//    @ConditionalOnMissingBean
//    public FastDfsMonitorTask fastDfsMonitorTask(FastDfsProperties properties, FastDfsMonitor fastDfsMonitor) {
//        FastDfsMonitorTask fastDfsMonitorTask = new FastDfsMonitorTask();
//        fastDfsMonitorTask.schedule(fastDfsMonitor, properties.getMonitor().getDelayMillis(),
//                properties.getMonitor().getPeriodMillis(), TimeUnit.MILLISECONDS);
//        LOGGER.info("fastDfs Monitor Task start...{}", fastDfsMonitorTask.getClass().getSimpleName());
//        return fastDfsMonitorTask;
//    }
}
