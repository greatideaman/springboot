package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/configuration")
public class ConfigurationController {

    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
        this.dao.addConfiguration("012018", new ConfigValue("Test-012018-1",0));
        this.dao.addConfiguration("012018", new ConfigValue("Test-012018-2",0));
        this.dao.addConfiguration("012018", new ConfigValue("Test-012018-3",0));
        this.dao.addConfiguration("012018", new ConfigValue("Test-012018-4",0));
        this.dao.addConfiguration("012018", new ConfigValue("Test-012018-5",0));

        this.dao.addConfiguration("022018", new ConfigValue("Test-202018-1",0));
        this.dao.addConfiguration("022018", new ConfigValue("Test-202018-2",0));
        this.dao.addConfiguration("022018", new ConfigValue("Test-202018-3",0));
        this.dao.addConfiguration("022018", new ConfigValue("Test-202018-4",0));
        this.dao.addConfiguration("022018", new ConfigValue("Test-202018-5",0));
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {

        return dao.getConfigurations(yearMonth);
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            dao.removeAllConfigurations(yearMonth);
        } catch (Exception ex) {

        }
    }

    @RequestMapping(value="/{yearMonthNumber}/{id}", method=RequestMethod.DELETE)
    public void deleteConfigurationForYearMonth(@PathVariable("yearMonthNumber") String yearMonth, @PathVariable("id") int id) {
        try {
            dao.removeConfiguration(yearMonth, id);
        } catch (Exception ex) {

        }
    }

    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody String name) {
                dao.addConfiguration(yearMonth, new ConfigValue(name,0));
    }

}
