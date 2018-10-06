/**
 * The Company Privacy & Copy Right message goes here
 */
package com.att.web.configuarations;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import com.att.exceptionHandling.ConfigurationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to provide CRUD functionality below by exposing REST API endpoints.
 *
 * Following CRUD operations are implemented:
 * 1. Display whatever is currently configured (not having a configuration is not an error) in a grid.
 * 2. Add new configurations for a time period.
 * 3. Relete all the configurations for a time period.
 * 4. Remove a single configuration for a time period.
 *
 */
@RestController
@RequestMapping(value = "/api/configuration")
@Api(value = "ConfigurationControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfigurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/findBy/{yearMonthNumber}", method = RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {
        try {
            return dao.getConfigurationsForYearMonth(yearMonth);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     *  Delete all the configurations for a time period.
     *
     * @param yearMonth
     */
    @RequestMapping(value = "/deleteall/{yearMonthNumber}", method = RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
            dao.removeAllConfigurationsForYearMonth(yearMonth);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Remove a single configuration for a time period
     *
     * @param configId
     * @param yearMonth
     */
    @RequestMapping(value = "/delete/{configId}/{yearMonthNumber}", method = RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(
            @PathVariable("configId") int configId,
            @PathVariable("yearMonthNumber") String yearMonth) {
        try {
            dao.removeConfigurationsForYearMonth(configId, yearMonth);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Add new configurations for a time period.
     *
     * @param yearMonth
     * @param value
     * @return ConfigValue
     */
    @RequestMapping(value = "/add/{yearMonthNumber}", method = { RequestMethod.POST, RequestMethod.PUT })
    @ApiOperation("Return the ConfigValue persisted with id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ConfigValue.class)})
    public ConfigValue addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth, @Valid
            @RequestBody ConfigValue value) throws ConfigurationException {
        return dao.addConfiguration(yearMonth, value);
    }
}
