package com.devil.zmq.broker.core;

import com.devil.zmq.broker.constants.LoggerName;
import com.devil.zmq.common.UtilAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConfigManager {
    protected static final Logger log = LoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);

    public boolean load() {
        String filename = null;
        try {
            filename = this.getConfigFilePath();
            String json = UtilAll.readFromFile(filename);
            if (json.isEmpty()) {
                return this.loadBak();
            } else {
                this.decode(json);
                log.info("load " + filename + " OK");
                return true;
            }
        } catch (Exception e) {
            log.error("load " + filename + " failed, and try to load backup file", e);
            return this.loadBak();
        }
    }

    private boolean loadBak() {
        String filename = null;
        try {
            filename = this.getConfigFilePath() + ".bak";
            String json = UtilAll.readFromFile(filename);
            if (!json.isEmpty()) {
                this.decode(json);
                log.info("load " + filename + " OK");
                return true;
            }
        } catch (Exception e) {
            log.error("load " + filename + " failed, and try to load backup file", e);
            return false;
        }
        return true;
    }

    public void persist() {
        String filename = this.getConfigFilePath();
        try {
            String json = this.encode(false);
            UtilAll.writeToFile(filename, json);
            log.info("persist " + filename + " OK");
        } catch (Exception e) {
            log.error("persist " + filename + " failed", e);
        }
    }

    public abstract String encode();

    public abstract String encode(final boolean prettyFormat);

    public abstract void decode(final String json);

    public abstract String getConfigFilePath();


}
