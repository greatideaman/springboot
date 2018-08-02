package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
//import java.awt.List;
import javax.print.attribute.HashAttributeSet;
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
        List<ConfigValue> list = new ArrayList<ConfigValue>();
        
        if(currentConfigurations.containsKey(yearMonth))
            {
                return currentConfigurations.get(yearMonth);
            }
            return new ArrayList<>();
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();
        ConfigValue newConfig = new ConfigValue();
        newConfig.setConfigId(newId);
        newConfig.setConfigName(value.getConfigName());
        List<ConfigValue> list = new ArrayList<ConfigValue>();
        list.add(value);
        currentConfigurations.put(yearMonth,list);
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        currentConfigurations.remove(yearMonth); 
    }
}
