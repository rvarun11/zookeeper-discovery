package com.varun.paymentservice;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceData {
    private final String ip;
    private final int port;

    public ServiceData(final JSONObject serviceData) throws JSONException {
        this.ip = serviceData.getString("IP");
        this.port = serviceData.getInt("Port");
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

}
