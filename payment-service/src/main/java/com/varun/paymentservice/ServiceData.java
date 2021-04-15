package com.varun.paymentservice;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceData {
    private final String uri;
    private final int port;

    public ServiceData(final JSONObject serviceData) throws JSONException {
        this.uri = serviceData.getString("URI");
        this.port = serviceData.getInt("Port");
    }

    public String getUri() {
        return uri;
    }

    public int getPort() {
        return port;
    }

}
