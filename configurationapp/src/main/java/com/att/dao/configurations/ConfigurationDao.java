package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import com.att.model.configurations.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationDao {
	
    private class IdProvider {
    	//@Autowired private Cache cache;
    	
        private int currentId;

        public IdProvider() {
            currentId = 0;
        }

        public int getNextId() {
            return this.currentId++;
        }
    }

    /**
     * No DB, so store the configs in a map.
     */
    private Map<String, List<ConfigValue>> currentConfigurations;
    private IdProvider idProvider;

    public ConfigurationDao() {
        idProvider = new IdProvider();
        currentConfigurations = new HashMap<>();
    }

    public List<ConfigValue> getConfigurations(Cache cache, String yearMonth) {
    	List<ConfigValue> configList = cache.getConfiguration(yearMonth);
        return configList;
    }

    public Cache addConfiguration(Cache cache, ConfigValue config) {
    	cache.addConfiguration(config);
    	
    	return cache;
    }
    
    public Cache deleteConfiguration(Cache cache, ConfigValue config) {
    	List<ConfigValue> configList = cache.getConfiguration(config.getYearMonth());
    	List<ConfigValue> deleteList = new ArrayList<ConfigValue>();
    	
    	for (int i=0; i<configList.size(); i++) {
    		if (configList.get(i).getConfigId() == config.getConfigId()) {
    			deleteList.add(configList.get(i));
    		}
    	}
    	
    	configList.removeAll(deleteList);
    	
    	return cache;
    }

    
    public void deleteAllConfigurations(Cache cache, String yearMonth) {
    	cache.removeConfiguration(yearMonth);
    }
}
