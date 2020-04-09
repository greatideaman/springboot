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
        currentConfigurations.put("012018", Arrays.asList(
            allConfigValues.get(0),
            allConfigValues.get(1),
            allConfigValues.get(2),
            allConfigValues.get(3)
        ));
        currentConfigurations.put("022018", Arrays.asList(
            allConfigValues.get(0),
            allConfigValues.get(2),
            allConfigValues.get(5),
            allConfigValues.get(6),
            allConfigValues.get(7)
        ));
    }

    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        List<ConfigValue> result = currentConfigurations.get(yearMonth);
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();

    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {

    }

    private ConfigValue createConfigValue(String name) {
        int id = idProvider.getNextId();
        ConfigValue cv = new ConfigValue(name, id);
        allConfigValues.put(id, cv);
        return cv;
    }

}
