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
            fastDfsProperties.put("clientConfiguration", client.getFastDfsConfiguration());
            fastDfsProperties.put("connectionConfig", client.getConnectionManager().getPool().getConnection());
            TrackerClient trackerClient = client.getTrackerClient();
            fastDfsProperties.put("trackerServers", trackerClient.getTrackerServers());

            List<GroupState> groupStates = trackerClient.listGroups();
            Map<String, Object> groupMap = new HashMap<>();
            groupStates.forEach(groupState -> {
                List<StorageState> storageStates = trackerClient.listStorages(groupState.getGroupName());
                groupMap.put("groupName", groupState.getGroupName());
                groupMap.put("groupState", groupState);
                groupMap.put("storageCount", storageStates.size());
                groupMap.put("storages", storageStates);
            });

            fastDfsProperties.put("groupCount", groupStates.size());
            fastDfsProperties.put("groups", groupMap);
            fastDfsClientList.add(fastDfsProperties);
        });

        result.put("size", size);
        result.put("info", fastDfsClientList);

        return result;
    }

}
