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

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {

            List<ConfigValue> getConfigValue = dao.getConfigurationsForYearMonth(yearMonth); 

        return getConfigValue;
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            dao.removeAllConfigurationsForYearMonth(yearMonth);
        } catch (Exception ex) {

        }
    }

    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue configValueIn) {
                dao.addConfiguration(yearMonth, configValueIn);        
    }
}
