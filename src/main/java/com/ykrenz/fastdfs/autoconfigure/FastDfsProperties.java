package com.ykrenz.fastdfs.autoconfigure;

import com.ykrenz.fastdfs.config.ConnectionConfiguration;
import com.ykrenz.fastdfs.config.HttpConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

@ConfigurationProperties(FastDfsConstants.PREFIX)
public class FastDfsProperties {
    /**
     * 是否开启
     */
    private boolean enabled;

    /**
     * trackerServers
     */
    private List<String> trackerServers;

    /**
     * 上传到固定分组
     */
    private String defaultGroup;

    /**
     * 连接配置
     */
    @NestedConfigurationProperty
    private ConnectionConfiguration connection = new ConnectionConfiguration();

    /**
     * http配置
     */
    @NestedConfigurationProperty
    private HttpConfiguration http = new HttpConfiguration();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getTrackerServers() {
        return trackerServers;
    }

    public void setTrackerServers(List<String> trackerServers) {
        this.trackerServers = trackerServers;
    }

    public String getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(String defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public ConnectionConfiguration getConnection() {
        return connection;
    }

    public void setConnection(ConnectionConfiguration connection) {
        this.connection = connection;
    }

    public HttpConfiguration getHttp() {
        return http;
    }

    public void setHttp(HttpConfiguration http) {
        this.http = http;
    }

}
