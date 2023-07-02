package com.ykrenz.fastdfs.autoconfigure.endpoint;

import com.ykrenz.fastdfs.FastDfsClient;
import com.ykrenz.fastdfs.TrackerClient;
import com.ykrenz.fastdfs.model.fdfs.GroupState;
import com.ykrenz.fastdfs.model.fdfs.StorageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Endpoint(id = "fastdfs")
public class FastDfsEndpoint {

    @Autowired
    private ApplicationContext applicationContext;

    public static final String UP = "UP";
    public static final String DOWN = "DOWN";

    @ReadOperation
    public Map<String, Object> invoke() {
        Map<String, Object> result = new HashMap<>();

        Map<String, FastDfsClient> fastDfsClientMap = applicationContext
                .getBeansOfType(FastDfsClient.class);

        int size = fastDfsClientMap.size();

        List<Object> fastDfsClientList = new ArrayList<>();

        fastDfsClientMap.keySet().forEach(beanName -> {
            Map<String, Object> fastDfsProperties = new HashMap<>();
            FastDfsClient client = fastDfsClientMap.get(beanName);
            fastDfsProperties.put("beanName", beanName);
            fastDfsProperties.put("configuration", client.getConfiguration());
            TrackerClient trackerClient = client.getTrackerClient();
            fastDfsProperties.put("trackerServers", trackerClient.getTrackerServers());

            try {
                List<GroupState> groupStates = trackerClient.listGroups();
                List<Map<String, Object>> groups = new ArrayList<>(groupStates.size());
                groupStates.forEach(groupState -> {
                    List<StorageState> storageStates = trackerClient.listStorages(groupState.getGroupName());
                    Map<String, Object> groupMap = new HashMap<>();
                    groupMap.put("group", groupState);
                    groupMap.put("storages", storageStates);
                    groups.add(groupMap);
                });
                fastDfsProperties.put("groups", groups);
                fastDfsProperties.put("status", UP);
            } catch (Exception e) {
                fastDfsProperties.put("status", DOWN);
            }

            fastDfsClientList.add(fastDfsProperties);
        });

        result.put("size", size);
        result.put("info", fastDfsClientList);

        return result;
    }


}
