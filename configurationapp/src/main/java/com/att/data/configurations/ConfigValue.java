package com.att.data.configurations;

/**
 * Data Model - Configuration Value
 */
public class ConfigValue {
	private String configName;
	private int configId;

	/**
	 * Constructor initialization with all properties.
	 * 
	 * @param name Configuration name
	 * @param id   Configuration id
	 */
	public ConfigValue(String name, int id) {
		this.configId = id;
		this.configName = name;
	}

	/**
	 * Default constructor.
	 */
	public ConfigValue() {

	}

	/**
	 * To set configuration name
	 * 
	 * @param name configuration name
	 */
	public void setConfigName(String name) {
		this.configName = name;
	}

	/**
	 * To get configuration name
	 * 
	 * @return configuration name
	 */
	public String getConfigName() {
		return this.configName;
	}

	/**
	 * To set configuration id.
	 * 
	 * @param name configuration id
	 */
	public void setConfigId(int id) {
		this.configId = id;
	}

	/**
	 * To get configuration id
	 * 
	 * @return configuration id
	 */
	public int getConfigId() {
		return this.configId;
	}
}
