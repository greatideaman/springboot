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
		List<ConfigValue> configForDate = currentConfigurations.get(yearMonth);
		if (configForDate == null) {
			configForDate = new ArrayList<ConfigValue>();
		}

        return configForDate;
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();
        value.setConfigId(newId);
        
        List<ConfigValue> configForDate = currentConfigurations.get(yearMonth);
        if (configForDate == null) {
        	configForDate = new ArrayList<ConfigValue>();
        }
    	configForDate.add(value);
    	currentConfigurations.put(yearMonth, configForDate);
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
    	currentConfigurations.remove(yearMonth);
    }
    
    public boolean deleteOneConfigForYearMonth(String yearMonth, ConfigValue value) {
    	boolean result = false;
    	List<ConfigValue> allConfigs4Date = currentConfigurations.get(yearMonth);
		if (allConfigs4Date != null) {
			for (int i = 0; i < allConfigs4Date.size(); i++) {
				ConfigValue listConfigValue = allConfigs4Date.get(i);
				if (listConfigValue.getConfigId() == value.getConfigId()) {
					allConfigs4Date.remove(i);
					result = true;
					break;
				}
			}
		}
		
		return result;
    }
}
