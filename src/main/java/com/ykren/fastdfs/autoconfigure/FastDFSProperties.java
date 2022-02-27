package com.ykren.fastdfs.autoconfigure;

import com.ykren.fastdfs.config.ConnectionConfiguration;
import com.ykren.fastdfs.config.HttpConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

import static com.ykren.fastdfs.autoconfigure.FastDFSConstants.PREFIX;

@ConfigurationProperties(PREFIX)
public class FastDFSProperties {

    /**
     * trackerServers
     */
    private List<String> trackerServers;

    /**
     * 上传到固定分组
     */
    private String groupName;

    @NestedConfigurationProperty
    private ConnectionConfiguration connection = new ConnectionConfiguration();

    @NestedConfigurationProperty
    private HttpConfiguration http = new HttpConfiguration();

    public List<String> getTrackerServers() {
        return trackerServers;
    }

    public void setTrackerServers(List<String> trackerServers) {
        this.trackerServers = trackerServers;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
