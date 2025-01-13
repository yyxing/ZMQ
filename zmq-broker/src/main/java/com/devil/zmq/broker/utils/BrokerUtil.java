package com.devil.zmq.broker.utils;

import cn.hutool.core.util.StrUtil;
import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.constants.Constants;

public class BrokerUtil {

    public static String getConfigPath() {
        String baseHome = CommonCache.getGlobalProperties().getBaseHome();
        if (StrUtil.isEmpty(baseHome)) {
            throw new IllegalArgumentException("ZMQ_HOME is null");
        }
        return baseHome + Constants.BROKER_CONFIG_PATH;
    }
}
