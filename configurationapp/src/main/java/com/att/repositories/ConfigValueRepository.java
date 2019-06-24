package com.att.repositories;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <h1>com.att.repositories.ConfigurationRepository</h1>
 * Description :
 *
 * @author gcanter
 * on 2019-06-23
 */


public interface ConfigValueRepository extends CrudRepository<ConfigValue, Long> {
    List<ConfigValue> findByConfigID( int ConfigID);

}
