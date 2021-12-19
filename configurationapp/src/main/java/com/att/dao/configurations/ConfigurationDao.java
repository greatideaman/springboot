package com.att.dao.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.att.data.configurations.ConfigValue;

/**
 * Data access layer implementation with basic CRUD operations, stores the data
 * in memory
 *
 */
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

    /**
     * Internal class to generate IDs. Similar to a database sequence.
     */
    private IdProvider idProvider;

    private static final String NO_NULLS_MSG = "Null values are not allowed";

    public ConfigurationDao() {
        idProvider = new IdProvider();
        currentConfigurations = new HashMap<>();

        init();

    }

    /**
     * Returns a list of configurations for the selected month
     * 
     * @param yearMonth
     * @return
     */
    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        if (yearMonth == null) {
            throw new IllegalArgumentException(NO_NULLS_MSG);
        }

        return currentConfigurations.get(yearMonth);
    }

    /**
     * Adds a single configuration, does not check for duplicates.
     * 
     * @param yearMonth
     * @param value
     */
    public void addConfiguration(String yearMonth, ConfigValue value) {

        if (yearMonth == null || value == null) {
            throw new IllegalArgumentException(NO_NULLS_MSG);
        }

        if (value.getConfigId() == -1) {
            // preserve existing, but assign new if does not exist
            value.setConfigId(idProvider.getNextId());

        }
        List<ConfigValue> list = currentConfigurations.get(yearMonth);
        if (list == null) {
            list = new ArrayList<ConfigValue>();
        }
        list.add(value);
        currentConfigurations.put(yearMonth, list);
    }

    /**
     * Removes all the configurations for the selected month
     * 
     * @param yearMonth
     */
    public void removeAllConfigurationsForYearMonth(String yearMonth) {
        if (yearMonth != null) {
            currentConfigurations.remove(yearMonth);
        }
    }

    /**
     * Removes all the configurations for the selected month
     * 
     * @param yearMonth
     */
    public void removeConfiguration(String yearMonth, int id) {
        if (yearMonth != null) {
            List<ConfigValue> list = currentConfigurations.get(yearMonth);
            for (ConfigValue c : list) {
                if (c.getConfigId() == id) {
                    list.remove(c);
                    break;
                }
            }
        }
    }

    /**
     * Removes all the configurations.
     */
    public void removeAllConfigurations() {
        currentConfigurations = new HashMap<>();
    }

    /**
     * Returns a map of all configurations
     * 
     * @return
     */
    public Map<String, List<ConfigValue>> getCurrentConfigurations() {
        // it should be a DB call here, so let's not bother with creating a deep copy
        return currentConfigurations;
    }

    private void init() {
        // should be equivalent to initial database call with a default query
        List<ConfigValue> list1 = new ArrayList<>();

        list1.add(new ConfigValue("A", idProvider.getNextId()));
        list1.add(new ConfigValue("B", idProvider.getNextId()));
        list1.add(new ConfigValue("C", idProvider.getNextId()));
        list1.add(new ConfigValue("D", idProvider.getNextId()));
        currentConfigurations.put("012018", list1);

        List<ConfigValue> list2 = new ArrayList<>();
        list2.add(new ConfigValue("A", idProvider.getNextId()));
        list2.add(new ConfigValue("C", idProvider.getNextId()));
        list2.add(new ConfigValue("F", idProvider.getNextId()));
        list2.add(new ConfigValue("G", idProvider.getNextId()));
        list2.add(new ConfigValue("H", idProvider.getNextId()));
        currentConfigurations.put("022018", list2);
    }

}
