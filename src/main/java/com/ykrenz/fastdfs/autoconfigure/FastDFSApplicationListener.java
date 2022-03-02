package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.FastDfs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;

public class FastDFSApplicationListener implements ApplicationListener<ContextClosedEvent> {

    private static final Logger log = LoggerFactory.getLogger(FastDFSApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Map<String, FastDfs> fastDfsClientMap = event.getApplicationContext().getBeansOfType(FastDfs.class);
        log.info("{} FastDFSClients will be shutdown soon", fastDfsClientMap.size());
        fastDfsClientMap.keySet().forEach(beanName -> {
            log.info("shutdown FastDFS Client: {}", beanName);
            fastDfsClientMap.get(beanName).shutdown();
        });
    }

}