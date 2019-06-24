package com.att.services;

import com.att.data.configurations.ConfigValue;

import java.util.List;

import com.att.data.configurations.ConfigValue;

/**
 * <h1>com.att.services.ConfigurationService</h1>
 * Description :
 *
 * @author gcanter
 * on 2019-06-23
 */


public interface ConfigValueService {
    List<ConfigValue> findByConfigDate();
    ConfigValue getConfigValueByName();
}
