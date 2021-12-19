package com.att.web.configuarations;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;

@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {

    private static final String ERROR_OCCURED = "Error occured";
    private ConfigurationDao dao;
    Logger log = LoggerFactory.getLogger(ConfigurationController.class);

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            log.debug("inside getConfigurationsForYearMonth, yearMonth=" + yearMonth);
        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
        }

        return dao.getConfigurationsForYearMonth(yearMonth);
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            log.debug("inside deleteConfigurationsForYearMonth");

            dao.removeAllConfigurationsForYearMonth(yearMonth);

        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
        }
    }

    @RequestMapping(value = "/{yearMonthNumber}/{id}", method = RequestMethod.DELETE)
    public void deleteConfiguration(@PathVariable("yearMonthNumber") String yearMonth, @PathVariable("id") int id) {
        try {
            log.debug("inside deleteConfiguration");

            dao.removeConfiguration(yearMonth, id);

        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
        }
    }

    @RequestMapping(value = "/{yearMonthNumber}", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addConfigurationForYearMonth(@PathVariable("yearMonthNumber") String yearMonth,
            @RequestParam String configName) {
        try {
            log.debug("inside addConfigurationForYearMonth");
            dao.addConfiguration(yearMonth, new ConfigValue(configName));
        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addConfiguration(@RequestParam String configName) {
        try {
            // default selection with all months
            addConfigurationForYearMonth("", configName);
        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<ConfigValue>> getCurrentConfigurations() {
        try {
            log.debug("inside getCurrentConfigurations");

            return dao.getCurrentConfigurations();
        } catch (Exception ex) {
            log.error(ERROR_OCCURED, ex);
            return null;
        }
    }
}
