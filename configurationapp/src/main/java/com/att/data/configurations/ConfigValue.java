package com.att.data.configurations;

/**
 * Data Model
 */
public class ConfigValue {
	private String yearMonth;
    private String configName;
    private int configId;


    public ConfigValue() {

    }
    
     public ConfigValue(String yearMonth, String configName, int configId) {
		super();
		this.yearMonth = yearMonth;
		this.configName = configName;
		this.configId = configId;
	}
     
     public ConfigValue(String yearMonth, String configName, String configId) {
		super();
		this.yearMonth = yearMonth;
		this.configName = configName;
		this.configId = Integer.parseInt(configId);
	}
     

	public void setConfigName(String name) {
        this.configName = name;
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigId(int id) {
        this.configId = id;
    }

    public int getConfigId() {
        return this.configId;
    }

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
    
    
}
