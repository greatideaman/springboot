package com.att.data.configurations;

import java.util.List;

/**
 * Data Model
 */
public class ConfigValues {
    private List<ConfigValue> data;

    public ConfigValues(List<ConfigValue> data){
        this.data = data;
    }

    public List<ConfigValue> getData() {
        return data;
    }

    public void setData(List<ConfigValue> data) {
        this.data = data;
    }
}
