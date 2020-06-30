package com.att.web.configuarations;

import com.att.data.configurations.ConfigValue;
import com.att.service.configurations.ConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/configurations")
public class ConfigurationController {

	@Autowired
    private ConfigurationService service;
    
    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {

        return service.getConfigurationsForYearMonth(yearMonth);
    }
    
    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT})
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {
    	
    	service.addConfiguration(yearMonth, value);

    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
    	
    	service.removeAllConfigurationsForYearMonth(yearMonth);
    }
    
    @RequestMapping(value="/{yearMonthNumber}/{configId}", method={ RequestMethod.DELETE})
    public void deleteConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @PathVariable("configId") int configId) {
    	
    	service.removeSingleConfigurationForYearMonth(yearMonth, configId);

    }
}
