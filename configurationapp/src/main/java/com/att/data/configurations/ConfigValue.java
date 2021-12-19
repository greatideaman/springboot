package com.att.data.configurations;

/**
 * Data Model, contains configuration name and configuration id values. Id is
 * equal to -1 by default if not provided.
 */
public class ConfigValue {
    private String configName;
    private int configId = -1; // not a required field

    public ConfigValue(String name, int id) {
        checkName(name);
        this.configId = id;
        this.configName = name;
    }

    public ConfigValue(String name) {
        checkName(name);
        this.configName = name;
    }

    public void setConfigName(String name) {
        checkName(name);
        this.configName = name;
    }

    private void checkName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("null or empty cofig name is not allowed");
        }
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigId(int id) {
        this.configId = id;
    }

    public int getConfigId() {
        return this.configId;
    }
}
