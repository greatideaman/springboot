/**
 * The Company Privacy & Copy Right message goes here
 */
package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
		if(currentConfigurations.containsKey(yearMonth)) {
			return currentConfigurations.get(yearMonth);
		}
		return null;
	}

    public ConfigValue addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();
        value.setConfigId(newId);
        if(currentConfigurations.containsKey(yearMonth)) {
            currentConfigurations.get(yearMonth).add(value);
        } else {
            List<ConfigValue> configValues = new ArrayList<ConfigValue>();
            configValues.add(value);
            currentConfigurations.put(yearMonth, configValues);
        }
        return value;

    }
    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        if(currentConfigurations.containsKey(yearMonth)) {
            currentConfigurations.get(yearMonth).clear();
        }
    }

    public void removeConfigurationsForYearMonth(int configId, String yearMonth) {
        if(currentConfigurations.containsKey(yearMonth)) {
            ConfigValue configValueSelected = currentConfigurations.get(yearMonth)
                    .stream()
                    .filter(c -> c.getConfigId() == configId)
                    .findFirst()
                    .get();
            currentConfigurations.get(yearMonth).remove(configValueSelected);
        }
    }
}
