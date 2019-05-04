package com.att.web.configuarations;

import java.util.ArrayList;
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

/**
 * The ConfigurationController class is used to manage the month year wise
 * configurations.
 * 
 * @author gayathri
 *
 */
@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

	private ConfigurationDao dao;

	@Autowired
	public ConfigurationController(ConfigurationDao dao) {
		this.dao = dao;
	}

	/**
	 * This request mapping is used to get all the configurations for the given
	 * month.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @return List<ConfigValue>
	 */
	@RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<ConfigValue> getConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
		List<ConfigValue> response = new ArrayList<ConfigValue>();
		try {
			LOGGER.debug("START : GET : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
			response = dao.getConfigurationsForYearMonth(yearMonth);
			LOGGER.debug("STOP : GET : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error("EXCEPTION : GET : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
		}
		return response;
	}

	/**
	 * This request mapping is used to delete all the configurations for the given
	 * month.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @return String formated JSON object response with msg key for status message.
	 */
	@RequestMapping(value = "/{yearMonthNumber}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
		String result = "Oops! Something went wrong while deleting the configuration for the selected month.";
		try {
			LOGGER.debug("START : DELETE : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
			result = dao.removeAllConfigurationsForYearMonth(yearMonth);
			LOGGER.debug("STOP : DELETE : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
		} catch (Exception ex) {
			LOGGER.error("EXCEPTION : DELETE : PATH - /{yearMonthNumber} of " + this.getClass().getSimpleName());
		}
		return "{\"msg\":\"" + result + "\"}";
	}

	/**
	 * This request mapping is used to add the given configuration for the given
	 * month.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @param value     ConfigValue object to be added
	 * @return String formated JSON object response with msg key for status message.
	 */
	@RequestMapping(value = "/{yearMonthNumber}", method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseBody
	public String addConfigurationForYearMonth(@PathVariable("yearMonthNumber") String yearMonth,
			@RequestBody ConfigValue value) {
		String result = "Oops! Something went wrong while adding new configuration.";
		try {
			LOGGER.debug("START : POST/PUT : PATH - /{yearMonthNumber} with Request Body of "
					+ this.getClass().getSimpleName());
			result = dao.addConfiguration(yearMonth, value);
			LOGGER.debug("STOP : POST/PUT : PATH - /{yearMonthNumber} with Request Body of "
					+ this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error("EXCEPTION : POST/PUT : PATH - /{yearMonthNumber} with Request Body of "
					+ this.getClass().getSimpleName());
		}
		return "{\"msg\":\"" + result + "\"}";
	}

	/**
	 * This request mapping is used to add the given configuration for the given
	 * month.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @param configId  Configuration Id to be deleted
	 * @return String formated JSON object response with msg key for status message.
	 */
	@RequestMapping(value = "/{yearMonthNumber}/{congigid}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOneConfigForYearMonth(@PathVariable("yearMonthNumber") String yearMonth,
			@PathVariable("congigid") Integer configId) {
		String response = "Oops! Something went wrong while deleting the selected configuration for the selected month.";
		try {
			LOGGER.debug("START : DELETE : PATH - /{yearMonthNumber}/{congigid} of " + this.getClass().getSimpleName());
			response = dao.removeOneConfigForYearMonth(yearMonth, configId);
			LOGGER.debug("STOP : DELETE : PATH - /{yearMonthNumber}/{congigid} of " + this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error(
					"EXCEPTION : DELETE : PATH - /{yearMonthNumber}/{congigid} of " + this.getClass().getSimpleName());
		}
		return "{\"msg\":\"" + response + "\"}";
	}

}
