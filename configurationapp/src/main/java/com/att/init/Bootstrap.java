package com.att.init;

import com.att.data.configurations.ConfigValue;
import com.att.repositories.ConfigValueRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * <h1>com.att.init.Bootstrap</h1>
 * Description :  This class seeds the database with a few values.  Does this thru Crudrepository/Spring/H2
 *
 * @author gcanter
 * on 2019-06-23
 */


// todo :: @log4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ConfigValueRepository configValueRepository;

    public Bootstrap(ConfigValueRepository configValueRepository) {
        this.configValueRepository = configValueRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


private void initData() {
    ConfigValue configValue = new ConfigValue();
    configValue.setConfigID(1);
    configValue.setConfigName("Alpha");
    configValue.setConfigDate("022019");

    configValueRepository.save(configValue);
}


}