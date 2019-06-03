package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {

    private ConfigurationDao dao;
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);


    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {

        List<ConfigValue> response = new ArrayList<ConfigValue>();
        try {
            logger.debug("Fetching configuration for year month : ${yearMonth}");
            response = dao.getConfigurationsForYearMonth(yearMonth);
        } catch (Exception e) {
            logger.error("Error fetching configuration for year month : ${yearMonth}. :: " + this.getClass().getName());
        }
        return response;
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            logger.debug("Deleting configuration for year month : ${yearMonth}");

            dao.deleteAllConfigurationsForYearMonth(yearMonth);
        } catch (Exception ex) {
            logger.error("Error deleting configurations for selected year and month. Class :: " + this.getClass().getName());
        }
    }

    @RequestMapping(value = "/{yearMonthNumber}/{configId}", method = RequestMethod.DELETE)
    public void deleteSelectedConfigForYearMonth(@PathVariable("yearMonthNumber") String yearMonth,
                                                 @PathVariable("configId") Integer configId) {
        try {
            logger.debug("Deleting selected configuration for year month : ${yearMonth}");
            dao.deleteSelectedConfigForYearMonth(yearMonth, configId);
        } catch (Exception e) {
            logger.error("Error deleting selected config for year and month. :: " + this.getClass().getName());
        }
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = {RequestMethod.POST})
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {

        try {
            logger.debug("Adding configuration for selected year and month : ${yearMonth}");
            dao.addConfigurationforYearMonth(yearMonth, value);
        } catch (Exception e) {
            logger.error("Error adding configuration for ${yearMonth}. :: " + this.getClass().getName());
        }


    }
}
