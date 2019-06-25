package com.att.repositories;

import com.att.model.Configvalues;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h1>com.att.repositories.ConfigvaluesRepository</h1>
 * Description :
 *
 * @author gcanter
 * on 2019-06-23
 */

@Repository
public interface ConfigvaluesRepository extends DataTablesRepository<Configvalues, Integer> {


    // TODO - CLEAN THIS UP
//    List<ConfigValue> findByConfigID( int ConfigID);
//    List<ConfigValue> findByConfigDate( String configDate);
//    void deleteConfigValuesByConfigDate( String configDate);
    void deleteAllByConfigDate(String configDate);
//    void deleteByConfigID(int configID);

}
