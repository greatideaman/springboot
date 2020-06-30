package com.att.service.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;

@Service
public class ConfigurationService {
	
	@Autowired
	private ConfigurationDao dao;
	
    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
        
    	return dao.getConfigurationsForYearMonth(yearMonth);
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
    	
    	dao.addConfiguration(yearMonth, value);
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
    	
    	dao.removeAllConfigurationsForYearMonth(yearMonth);
    }
    
    public void removeSingleConfigurationForYearMonth(String yearMonth, int configId) {
    	
    	dao.removeSingleConfigurationForYearMonth(yearMonth, configId);
    }

}
