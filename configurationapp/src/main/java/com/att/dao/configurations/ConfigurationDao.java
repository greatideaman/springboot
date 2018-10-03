package com.att.dao.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.att.data.configurations.ConfigValue;

@Service
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
    	if(isConfigurationKeyExist(yearMonth)){
    		return currentConfigurations.get(yearMonth);
    	}
        return new ArrayList<>();
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();
        value.setConfigId(newId);
        if(isConfigurationKeyExist(yearMonth)){
        	List<ConfigValue> values= currentConfigurations.get(yearMonth);        	
        	values.add(value);
        	currentConfigurations.put(yearMonth, values);
        }else{
        	List<ConfigValue> values = new ArrayList<>();        	
        	values.add(value);
        	currentConfigurations.put(yearMonth, values);
        }
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
    		if(isConfigurationKeyExist(yearMonth)){
    			currentConfigurations.remove(yearMonth);
    		}else{
    			throw new RuntimeException("Configuration does not exist");
    		}
    		
    }
    
    public void removeSelecteConfigurationForYearMonth(String yearMonth, int configId){
    	if(isConfigurationKeyExist(yearMonth)){
    		List<ConfigValue> values = currentConfigurations.get(yearMonth);
    		values.removeIf(value -> value.getConfigId() == configId);    		
    	} 
    }
    
    private boolean isConfigurationKeyExist(String key){
    	return currentConfigurations.containsKey(key);
    }
}
