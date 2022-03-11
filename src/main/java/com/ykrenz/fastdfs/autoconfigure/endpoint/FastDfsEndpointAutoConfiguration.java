package com.ykrenz.fastdfs.autoconfigure.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(Endpoint.class)
public class FastDfsEndpointAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastDfsEndpoint fastDfsEndpoint() {
        return new FastDfsEndpoint();
    }

}