package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import java.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/configuration")
public class ConfigurationController {

    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/getConfigurationsForYearMonth/{yearMonth}", method=RequestMethod.GET) 
    public  List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonth") String yearMonth) {

            List<ConfigValue> getConfigValue = dao.getConfigurationsForYearMonth(yearMonth); 

        return getConfigValue;
    }
    
    @RequestMapping(value="/deleteConfigurationsForYearMonth/{yearMonthNumber}/{deleteAll}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(
        @PathVariable("deleteAll") Boolean deleteAll,
        @PathVariable("yearMonthNumber") String yearMonth,
        @RequestBody ConfigValue configValueIn) {
        
        if (deleteAll) {
            try {
                dao.removeAllConfigurationsForYearMonth(yearMonth);
            } catch (Exception ex) {

            }
        } else {
            dao.removeConfigurationsForYearMonth(yearMonth,configValueIn);
        }
    }

    @RequestMapping(value="/addConfigurationForYearMonth/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public int addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue configValueIn) {
            return(dao.addConfiguration(yearMonth, configValueIn));        
    }
}
