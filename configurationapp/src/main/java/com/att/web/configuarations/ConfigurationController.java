package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {
        ;
        return dao.getConfigurationsForYearMonth(yearMonth);
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public String deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        dao.removeAllConfigurationsForYearMonth(yearMonth);
        return "{\"Status\":\"SUCCESS\"}";
    }

    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public String addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {
        dao.addConfiguration(yearMonth, value);
        return "{\"Status\":\"SUCCESS\"}";
    }
}
