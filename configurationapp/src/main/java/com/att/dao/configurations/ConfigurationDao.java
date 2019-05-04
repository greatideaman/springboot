package com.att.dao.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.att.data.configurations.ConfigValue;

/**
 * The ConfigurationDao class is used to manage the month year wise
 * configurations.
 * 
 * @author gayathri
 *
 */
@Service
public class ConfigurationDao {

	/**
	 * Simple Class to provide sequence id to configurations.
	 * 
	 * @author gayathri
	 *
	 */
	private class IdProvider {
		private int currentId;

		/**
		 * Constructor initialization with sequence to zero.
		 */
		public IdProvider() {
			currentId = 0;
		}

		/**
		 * To get next sequence id for the configuration.
		 * 
		 * @return next sequence id
		 */
		public int getNextId() {
			return this.currentId++;
		}
	}

	/**
	 * No DB, so store the configs in a map.
	 */
	private Map<String, List<ConfigValue>> currentConfigurations;
	private IdProvider idProvider;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationDao.class);

	/**
	 * Initializing default constructor.
	 */
	public ConfigurationDao() {
		idProvider = new IdProvider();
		currentConfigurations = new HashMap<>();
	}

	/**
	 * To get the configurations of the given month.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @return List<ConfigValue>
	 */
	public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
		try {
			LOGGER.debug("START : getConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
			if (currentConfigurations != null && yearMonth != null && yearMonth.trim().length() > 0) {
				List<ConfigValue> configurations = currentConfigurations.get(yearMonth);
				if (configurations == null) {
					configurations = new ArrayList<ConfigValue>();
				}
				LOGGER.debug("STOP : getConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
				return configurations;
			}
		} catch (Exception e) {
			LOGGER.error("EXCEPTION : getConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
		}
		return new ArrayList<ConfigValue>();
	}

	/**
	 * To add the new configuration for the given month year.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @param value     Config Value to be added.
	 * @return result
	 */
	public String addConfiguration(String yearMonth, ConfigValue value) {
		String result = "Oops! Something went wrong while adding new configuration.";
		try {
			LOGGER.debug("START : addConfiguration(String, ConfigValue) of " + this.getClass().getSimpleName());
			if (currentConfigurations != null && yearMonth != null && yearMonth.trim().length() > 0 && value != null) {
				int newId = idProvider.getNextId();
				value.setConfigId(newId);

				List<ConfigValue> configurations = currentConfigurations.get(yearMonth);
				if (configurations == null) {
					configurations = new ArrayList<ConfigValue>();
					configurations.add(value);
					currentConfigurations.put(yearMonth, configurations);
					result = "Given configuration has been successfully added for the selected month.";
				} else {
					Optional<ConfigValue> configValueOpt = configurations.stream().filter(configValueData -> {
						return configValueData.getConfigName().equals(value.getConfigName());
					}).findFirst();
					if (configValueOpt.isPresent()) {
						result = "Configuration with given name already exists for the selected month.";
					} else {
						configurations.add(value);
						currentConfigurations.put(yearMonth, configurations);
						result = "Given configuration has been successfully added for the selected month.";
					}
				}
			}
			LOGGER.debug("STOP : addConfiguration(String, ConfigValue) of " + this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error("EXCEPTION : addConfiguration(String, ConfigValue) of " + this.getClass().getSimpleName());
		}
		return result;
	}

	/**
	 * To remove all the configuration for the given month year.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @return result
	 */
	public String removeAllConfigurationsForYearMonth(String yearMonth) {
		String result = "Oops! Something went wrong while deleting the configurations for the selected month.";
		try {
			LOGGER.debug("START : removeAllConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
			if (currentConfigurations != null && yearMonth != null && yearMonth.trim().length() > 0) {
				List<ConfigValue> configValues = currentConfigurations.remove(yearMonth);
				if (configValues != null && configValues.size() > 0) {
					result = "All the configurtions for the selected month got deleted successfully.";
				} else {
					result = "Selected month don't have any configurtions asscoiated with it.";
				}
			}
			LOGGER.debug("STOP : removeAllConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error(
					"EXCEPTION : removeAllConfigurationsForYearMonth(String) of " + this.getClass().getSimpleName());
		}
		return result;
	}

	/**
	 * To remove given configuration for the given month year based on config id.
	 * 
	 * @param yearMonth year month like 0520119 for May 2019.
	 * @param value     Config Value to be added.
	 */
	public String removeOneConfigForYearMonth(String yearMonth, Integer configId) {
		boolean result = false;
		String response = "Oops! Something went wrong while deleting the selected configuration for the selected month.";
		try {
			LOGGER.debug(
					"START : removeOneConfigForYearMonth(String, ConfigValue) of " + this.getClass().getSimpleName());
			if (currentConfigurations != null && yearMonth != null && yearMonth.trim().length() > 0 && configId != null
					&& configId >= 0) {
				List<ConfigValue> configurations = currentConfigurations.get(yearMonth);
				if (configurations != null) {
					for (int i = 0; i < configurations.size(); i++) {
						ConfigValue listConfigValue = configurations.get(i);
						if (listConfigValue.getConfigId() == configId) {
							configurations.remove(i);
							result = true;
							break;
						}
					}
					if (result == true) {
						response = "Successfully deleted the selected configuration for the selected month.";
					} else {
						response = "The selected configuration is not found for the selected month.";
					}
				}
			}
			LOGGER.debug(
					"STOP : removeOneConfigForYearMonth(String, ConfigValue) of " + this.getClass().getSimpleName());
		} catch (Exception e) {
			LOGGER.error("EXCEPTION : removeOneConfigForYearMonth(String, ConfigValue) of "
					+ this.getClass().getSimpleName());
		}
		return response;
	}
}
