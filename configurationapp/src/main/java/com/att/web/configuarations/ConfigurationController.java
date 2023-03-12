package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/configuration")
@Slf4j
public class ConfigurationController {

  private static final String ERROR_OCCURED = "Error occured";

  private final ConfigurationDao configurationDao;

  @Autowired
  public ConfigurationController(ConfigurationDao dao) {
    this.configurationDao = dao;
  }

  @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.GET)
  @ResponseBody
  public List<ConfigValue> getConfigurationsForYearMonth(
      @PathVariable("yearMonthNumber") String yearMonth) {
    return configurationDao.getConfigurationsForYearMonth(yearMonth);
  }

  @RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.DELETE)
  public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
    try {
      configurationDao.removeAllConfigurationsForYearMonth(yearMonth);
    } catch (Exception ex) {
      log.error(ERROR_OCCURED, ex);
    }
  }

  @RequestMapping(value = "singleConfig/{yearMonthNumber}", method = RequestMethod.DELETE)
  public void deleteSingleConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth, @RequestParam("configId") int configId) {
    try {
      configurationDao.removeConfiguration(yearMonth, configId);
    } catch (Exception ex) {
      log.error(ERROR_OCCURED, ex);
    }
  }

  @RequestMapping(
      value = "/{yearMonthNumber}",
      method = {RequestMethod.POST, RequestMethod.PUT})
  public void addConfigurationForYearMonth(
      @PathVariable("yearMonthNumber") String yearMonth, @RequestBody ConfigValue value) {
    configurationDao.addConfiguration(yearMonth, value);
  }
}
