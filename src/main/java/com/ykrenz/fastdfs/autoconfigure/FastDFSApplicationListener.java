package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.FastDFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;

public class FastDFSApplicationListener implements ApplicationListener<ContextClosedEvent> {

    private static final Logger log = LoggerFactory.getLogger(FastDFSApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Map<String, FastDFS> fastDFSClientMap = event.getApplicationContext()
                .getBeansOfType(FastDFS.class);
        log.info("{} FastDFSClients will be shutdown soon", fastDFSClientMap.size());
        fastDFSClientMap.keySet().forEach(beanName -> {
            log.info("shutdown FastDFS Client: {}", beanName);
            fastDFSClientMap.get(beanName).shutdown();
        });
    }

}