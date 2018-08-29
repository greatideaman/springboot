package com.att.data.configurations;

/**
 * Data Model
 */
public class ConfigValue {
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

	@Override
	public String toString() {
		return "ConfigValue [configName=" + configName + ", configId=" + configId + "]";
	}

}
