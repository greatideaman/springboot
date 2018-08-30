package com.att.web.configuarations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger log = LoggerFactory.getLogger(ConfigurationController.class);
	
    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }
   
    @RequestMapping(value="/getConfigurationsForYearMonth/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
    	
    	log.debug("In getConfigurationsForYearMonth with " + yearMonth);
    	
    	return dao.getConfigurationsForYearMonth(yearMonth);
    }
    
    @RequestMapping(value="deleteConfigurationsForYearMonth/{yearMonthNumber}", method=RequestMethod.DELETE)
    public boolean deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
    	//Not sure why you have exception block here, but minimally need to log an exception when it is caught
        try {
        	log.debug("In deleteConfigurationsForYearMonth " + yearMonth);
        	dao.removeAllConfigurationsForYearMonth(yearMonth);
        	return true;
        } catch (Exception ex) {
        	log.error("Exception while deleteConfigurationsForYearMonth for" + yearMonth);
            return false;
        }
    }
    
    @RequestMapping(value="/addConfigurationForYearMonth/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public boolean addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {

    		log.debug("In addConfigurationForYearMonth " + yearMonth);
    		log.debug(value.toString());
    		
    		dao.addConfiguration(yearMonth, value);
    		
    		return true;
    }
    
    @RequestMapping(value="deleteOneConfigForYearMonth/{yearMonthNumber}", method=RequestMethod.DELETE)
    public boolean deleteOneConfigForYearMonth(
    		@PathVariable("yearMonthNumber") String yearMonth,
    		@RequestBody ConfigValue value) {
    	
    	log.debug("In deleteOneConfigForYearMonth " + yearMonth);
    	log.debug(value.toString());
    	return dao.deleteOneConfigForYearMonth(yearMonth, value);
    }
    
}
