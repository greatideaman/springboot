package com.att.model.configurations;

import java.util.List;

import com.att.data.configurations.ConfigValue;

public interface Cache {
	public void addConfiguration(  ConfigValue configuration);
	public List<ConfigValue> getConfiguration(String yearMonth);
	public void removeConfiguration(String yearMonth);
}
