package com.varun.orderservice;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:application.properties")
public class ServiceManager {
    private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);
    private final String BASE_PATH = "/services";
    private final String SERVICE_NAME;
    private final String SERVICE_IP;
    private final int SERVICE_PORT;
    private final String SERVICE_PATH;

    @Autowired
    private CuratorFramework client;

    public ServiceManager(@Value("${service.name}") final String serviceName,
                          @Value("${service.ip}") final String serviceIp,
                          @Value("${service.port}") final int servicePort) {
        SERVICE_NAME = serviceName;
        SERVICE_IP = serviceIp;
        SERVICE_PORT = servicePort;
        SERVICE_PATH = BASE_PATH + '/' + SERVICE_NAME;
    }

    @PostConstruct
    private void registerService() throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(SERVICE_PATH);
        setConfig(client);
    }

    private void setConfig(CuratorFramework client) throws Exception {
        JSONObject jo = new JSONObject();
        //using the service IP won't work.
        //String serviceIP = InetAddress.getLocalHost().getHostAddress();
        jo.put("IP", SERVICE_IP);
        jo.put("Port", SERVICE_PORT);

        client.setData().forPath(SERVICE_PATH, jo.toString().getBytes());

        log.info(String.format("%s has been registered with IP: %s and Port: %d", SERVICE_NAME, SERVICE_IP, SERVICE_PORT));
    }

    public JSONObject getService(String serviceName) throws Exception {
        String path = BASE_PATH + '/' + serviceName;
        if (client.checkExists().forPath(path) == null) {
            throw new RuntimeException("No such service instance is running");
        }
        String data = new String(client.getData().forPath(path));
        log.info(String.format("%s was received with config: %s", serviceName, data));
        return new JSONObject(data);
    }
}

