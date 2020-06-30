package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConfigurationDao {
    private class IdProvider {
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

    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
    	
    	List<ConfigValue> configValues = currentConfigurations.get(yearMonth);
        
    	return (configValues != null ? configValues : new ArrayList<>());
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
    	
        int newId = idProvider.getNextId();
        value.setConfigId(newId);
        
        if (currentConfigurations.containsKey(yearMonth)) {
        	currentConfigurations.get(yearMonth).add(value);
        }
        else {
        	
        	List<ConfigValue> configValues = new ArrayList<ConfigValue>();
        	configValues.add(value);
        	
        	currentConfigurations.put(yearMonth, configValues);
        }
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
    	
    	currentConfigurations.remove(yearMonth);
    }
    
    public void removeSingleConfigurationForYearMonth(String yearMonth, int configId) {

    	if (currentConfigurations.containsKey(yearMonth)) {
    		currentConfigurations.get(yearMonth).removeIf(cv -> (cv.getConfigId() == configId));
    	}
    }
}
