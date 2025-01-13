package com.devil.zmq.broker.config;

import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.constants.Constants;
import io.netty.util.internal.StringUtil;

public class GlobalPropertiesLoader {

    private GlobalProperties globalProperties;

    public GlobalPropertiesLoader() {
        this.loadProperties();
    }

    public void loadProperties() {
        globalProperties = new GlobalProperties();
        String baseHome = System.getenv(Constants.ZMQ_HOME);
        if (StringUtil.isNullOrEmpty(baseHome)) {
            throw new IllegalArgumentException("ZMQ_HOME is null");
        }
        globalProperties.setBaseHome(baseHome);
        CommonCache.setGlobalProperties(globalProperties);
    }
}
