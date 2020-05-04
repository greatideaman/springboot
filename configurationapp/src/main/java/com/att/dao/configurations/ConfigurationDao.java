package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import org.springframework.stereotype.Service;

//import javafx.util.converter.CurrencyStringConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

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

    public List<ConfigValue> getConfigurations(String yearMonth) {
        if (currentConfigurations.containsKey(yearMonth)) {
            return currentConfigurations.get(yearMonth);
        }
        else {
            return new ArrayList<>();
        }
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        if (!currentConfigurations.containsKey(yearMonth)) {
            currentConfigurations.put(yearMonth,new ArrayList<>());
        }
        if (currentConfigurations.containsKey(yearMonth)) {
            value.setConfigId(idProvider.getNextId());
            currentConfigurations.get(yearMonth).add(value);
        }
    }

    public void removeConfiguration(String yearMonth, int id) {
        if (currentConfigurations.containsKey(yearMonth)) {
            Iterator<ConfigValue> i = currentConfigurations.get(yearMonth).iterator();
            while (i.hasNext()) {
                ConfigValue item = i.next();
                if (item.getConfigId() == id) {
                    i.remove();
                }
            }
        }
    }

    public void removeAllConfigurations(String yearMonth) {
        if (currentConfigurations.containsKey(yearMonth)) {
            currentConfigurations.get(yearMonth).clear();
        }
    }
}
