package com.devil.zmq.broker.utils;

import cn.hutool.core.util.StrUtil;
import com.devil.zmq.broker.constants.Constants;

import java.io.File;

public class CommitLogUtil {
    public static String generateFileName(int index) {
        return StrUtil.padPre(String.valueOf(index), 8, "0");
    }

    public static String buildAbsolutePath(String topic, int index) {
        return Constants.getMqHome() + File.separator +
                Constants.BROKER_COMMIT_LOG_PATH + topic + File.separator + generateFileName(index);
    }


}
