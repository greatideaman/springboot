package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if(null == currentConfigurations.get(yearMonth)) {
            return new ArrayList<>();
        }
        return currentConfigurations.get(yearMonth);
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        List<ConfigValue> adding = new ArrayList<>();
        if(currentConfigurations.containsKey(yearMonth)) {
            adding = currentConfigurations.get(yearMonth);
            adding.add(new ConfigValue(value.getConfigName(),idProvider.getNextId()));
        } else {
            adding.add(new ConfigValue(value.getConfigName(),idProvider.getNextId()));
        }
        currentConfigurations.put(yearMonth, adding);
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        currentConfigurations = new HashMap<>();
    }
}
