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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof ConfigValue))
            return false;
        
        ConfigValue other = (ConfigValue)o;
        return this.configId == other.configId;
    }
    
    @Override
    public final int hashCode() {
        int result = 17;
        result = 31 * result + configId;
        return result;
    }
}
