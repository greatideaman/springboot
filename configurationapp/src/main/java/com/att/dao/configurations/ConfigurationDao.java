package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
//import java.awt.List;
import com.sun.org.apache.xpath.internal.operations.And;
import javax.print.attribute.HashAttributeSet;
import org.springframework.stereotype.Service;

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

    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        List<ConfigValue> list = new ArrayList<ConfigValue>();
        
        if(currentConfigurations.containsKey(yearMonth))
            {
                return currentConfigurations.get(yearMonth);
            }
            return new ArrayList<>();
    }

    public int addConfiguration(String yearMonth, ConfigValue value) {
        int newId = idProvider.getNextId();
        value.setConfigId(newId);
        List<ConfigValue> list = new ArrayList<ConfigValue>();
        if (currentConfigurations.containsKey(yearMonth)) {
            list = currentConfigurations.get(yearMonth);
            list.add(value);
        } else {      
            list.add(value);
        }
        currentConfigurations.put(yearMonth,list);
        return newId;
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        currentConfigurations.remove(yearMonth); 
    }

    public void removeConfigurationsForYearMonth(String yearMonth, ConfigValue value) {
        List<ConfigValue> list = new ArrayList<ConfigValue>();
        if (currentConfigurations.containsKey(yearMonth)) {
            list = currentConfigurations.get(yearMonth);
            Iterator<ConfigValue> iterator = list.iterator();
            List<ConfigValue> listAfter = new ArrayList<>();
            while(iterator.hasNext()) {
                ConfigValue next = iterator.next();
                    if(next.getConfigId() ==  value.getConfigId() &&
                        next.getConfigName().equals(value.getConfigName())) {
                        
                    } else {
                        listAfter.add(next);
                    }
                }
           
            currentConfigurations.remove(yearMonth);
            currentConfigurations.put(yearMonth,listAfter);
        } 
    }

    
}
