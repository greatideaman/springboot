package com.att.model.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.att.data.configurations.ConfigValue;

@Component
@Scope("session")
public class CacheImpl implements Cache {
	private Map<String, List<ConfigValue>> cache = new HashMap<String, List<ConfigValue>>();

	public void addConfiguration( ConfigValue config) {
		List<ConfigValue> configList = cache.get(config.getYearMonth());
		
		if (configList == null) {
			configList = new ArrayList<ConfigValue>();
		}
		
		config.setConfigId(configList.size() + 1);
		configList.add(config);
		
		cache.put(config.getYearMonth(), configList);
		
	}
	
	public List<ConfigValue> getConfiguration(String yearMonth) {
		return cache.get(yearMonth);
	}
	
	public void removeConfiguration(String yearMonth) {
		cache.remove(yearMonth);
	}
	
}
