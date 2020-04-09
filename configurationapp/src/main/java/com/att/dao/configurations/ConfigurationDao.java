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
    private final Map<String, List<ConfigValue>> currentConfigurations;
    private final Map<Integer, ConfigValue> allConfigValues;
    private final IdProvider idProvider;

    public ConfigurationDao() {
        idProvider = new IdProvider();
        // Acknowledging that configurations are different subsets over the same universe of config values
        allConfigValues = new HashMap<>();
        createConfigValue("A");
        createConfigValue("B");
        createConfigValue("C");
        createConfigValue("D");
        createConfigValue("E");
        createConfigValue("F");
        createConfigValue("G");
        createConfigValue("H");
        currentConfigurations = new HashMap<>();
        ArrayList<ConfigValue> configValues = new ArrayList<>();
        configValues.add(allConfigValues.get(0));
        configValues.add(allConfigValues.get(1));
        configValues.add(allConfigValues.get(2));
        configValues.add(allConfigValues.get(3));
        currentConfigurations.put("012018", configValues);
        configValues = new ArrayList<>();
        configValues.add(allConfigValues.get(0));
        configValues.add(allConfigValues.get(2));
        configValues.add(allConfigValues.get(5));
        configValues.add(allConfigValues.get(6));
        configValues.add(allConfigValues.get(7));
        currentConfigurations.put("022018", configValues);
    }

    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        List<ConfigValue> result = currentConfigurations.get(yearMonth);
        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }

    public void addConfiguration(String yearMonth, ConfigValue prototype) {
        ConfigValue newValue = createConfigValue(prototype.getConfigName());
        List<ConfigValue> values = currentConfigurations.get(yearMonth);
        if (values == null) {
            values = new ArrayList<>();
            currentConfigurations.put(yearMonth, values);
        }
        values.add(newValue);
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        currentConfigurations.remove(yearMonth);
    }

    private ConfigValue createConfigValue(String name) {
        int id = idProvider.getNextId();
        ConfigValue cv = new ConfigValue(name, id);
        allConfigValues.put(id, cv);
        return cv;
    }

}
