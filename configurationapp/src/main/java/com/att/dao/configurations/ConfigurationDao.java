package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import com.att.web.configuarations.ConfigurationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigurationDao {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationDao.class);

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

    public void addConfigurationforYearMonth(String yearMonth, ConfigValue value) {
        try {
            if (value != null && !isNullOrEmpty(yearMonth)) {
                value.setConfigId(idProvider.getNextId());

                List<ConfigValue> configurationList = currentConfigurations.get(yearMonth);
                if (isConfigExist(yearMonth)) {
                    Optional<ConfigValue> existingConfigValue = configurationList
                            .stream()
                            .filter(data -> data.getConfigName().equals(value.getConfigName()))
                            .findFirst();

                    if (!existingConfigValue.isPresent()) {
                        configurationList.add(value);
                        currentConfigurations.put(yearMonth, configurationList);
                    }
                } else {
                    configurationList = new ArrayList<ConfigValue>();
                    configurationList.add(value);
                    currentConfigurations.put(yearMonth, configurationList);
                }
            }
        } catch (Exception e) {
            logger.error("Exception adding configuration for selected year and month. " + e.getMessage());
        }
    }


    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        try {
            if (isConfigExist(yearMonth) && !isNullOrEmpty(yearMonth)) {
                return currentConfigurations.get(yearMonth);
            }
        } catch (Exception e) {
            logger.error("Error fetching configurations for selected year and month. " + e.getMessage());
        }
        return new ArrayList<>();
    }


    public void deleteAllConfigurationsForYearMonth(String yearMonth) {

        try {
            if (!isNullOrEmpty(yearMonth)) {
                currentConfigurations.remove(yearMonth);
            }
        } catch (Exception e) {
            logger.error("Error deleting config for year and month " + e.getMessage());
        }
    }

    public void deleteSelectedConfigForYearMonth(String yearMonth, int configId) {
        try {
            if (!isNullOrEmpty(yearMonth) && isConfigExist(yearMonth)) {
                currentConfigurations.get(yearMonth).removeIf(it -> it.getConfigId() == configId);
            }
        } catch (Exception e) {
            logger.error("Error deleting selected config for year and month. " + e.getMessage());
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return !(str != null && !str.trim().isEmpty());
    }

    private boolean isConfigExist(String key) {
        return currentConfigurations.containsKey(key);
    }
}
