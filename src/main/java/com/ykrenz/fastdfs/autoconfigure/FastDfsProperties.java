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

    /**
     * 监控配置
     */
    private Monitor monitor = new Monitor();

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

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    static class Monitor {
        /**
         * 是否开启监控
         */
        private boolean enabled;

        /**
         * storage磁盘监控  tracker.conf reserved_storage_space
         */
        private String reservedStorageSpace = "25%";

        /**
         * 周期时间
         */
        private long periodMillis = 30 * 1000L;

        /**
         * 延迟执行
         */
        private long delayMillis = 0L;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getReservedStorageSpace() {
            return reservedStorageSpace;
        }

        public void setReservedStorageSpace(String reservedStorageSpace) {
            this.reservedStorageSpace = reservedStorageSpace;
        }

        public long getPeriodMillis() {
            return periodMillis;
        }

        public void setPeriodMillis(long periodMillis) {
            this.periodMillis = periodMillis;
        }

        public long getDelayMillis() {
            return delayMillis;
        }

        public void setDelayMillis(long delayMillis) {
            this.delayMillis = delayMillis;
        }
    }
}
