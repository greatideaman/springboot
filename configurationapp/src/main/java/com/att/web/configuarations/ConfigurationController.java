package com.att.web.configuarations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;

@RestController
@RequestMapping(value="/configuration")
public class ConfigurationController {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/get/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {
    	
    	if(yearMonth != null && !yearMonth.isEmpty()){
    		return dao.getConfigurationsForYearMonth(yearMonth);
    	}
    	
        return new ArrayList<>();
    }

    @RequestMapping(value="/delete/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
        	if(yearMonth != null && !yearMonth.isEmpty()){
        		dao.removeAllConfigurationsForYearMonth(yearMonth);
        	}
        } catch (Exception ex) {
        	logger.error(ex.getMessage());
        }
    }
    

    @RequestMapping(value="/delete/{yearMonthNumber}/{configId}", method=RequestMethod.DELETE)
    public void deleteSelectedConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth,
    		@PathVariable("configId") Integer configurationId) {
       
        	if(yearMonth != null && !yearMonth.isEmpty() && configurationId != null){
        		dao.removeSelecteConfigurationForYearMonth(yearMonth,configurationId);
        	
        	}
    }

    @RequestMapping(value="/add/{yearMonthNumber}", method={RequestMethod.POST}, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @Valid @RequestBody ConfigValue value) {
    	if(yearMonth != null && !yearMonth.isEmpty()){
    		dao.addConfiguration(yearMonth, value);
    	} 	  	

    }
}
