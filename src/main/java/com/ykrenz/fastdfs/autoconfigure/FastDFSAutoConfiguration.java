package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.FastDfs;
import com.ykrenz.fastdfs.FastDfsClientBuilder;
import com.ykrenz.fastdfs.config.FastDFSConfiguration;
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
@ConditionalOnProperty(name = FastDFSConstants.ENABLED, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(FastDFSProperties.class)
public class FastDFSAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public FastDfs fastDfs(FastDFSProperties properties) {
        Assert.isTrue(!CollectionUtils.isEmpty(properties.getTrackerServers()), "FastDFS trackerServers can't be empty.");
        FastDFSConfiguration configuration = new FastDFSConfiguration();
        configuration.setDefaultGroup(properties.getDefaultGroup());
        configuration.setHttp(properties.getHttp());
        configuration.setConnection(properties.getConnection());
        return new FastDfsClientBuilder().build(properties.getTrackerServers(), configuration);
    }
}
