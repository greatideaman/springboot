package com.att.data.configurations;

import javax.validation.constraints.NotNull;

/**
 * Data Model
 */
public class ConfigValue {
	
	@NotNull(message = "config name must not be null")
    private String configName;
    private int configId;

    public ConfigValue(String name, int id) {
        this.configId = id;
        this.configName = name;
    }

    public ConfigValue() {

    }

    public void setConfigName(String name) {
        this.configName = name;
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
