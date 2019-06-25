package com.att.Init;

import com.att.model.Configvalues;
import com.att.repositories.ConfigvaluesRepository;
//import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Random;

@Service
@Slf4j
class ConfigvaluesInitializer {
    private static final int NUMBER_TO_GENERATE = 4;

    private ConfigvaluesRepository configvaluesRepository;

    public ConfigvaluesInitializer(ConfigvaluesRepository configvaluesRepository) {
        this.configvaluesRepository = configvaluesRepository;
    }

    @PostConstruct
    public void init() {
        log.info("generating {} random employees", NUMBER_TO_GENERATE);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");


        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("012019");
        arrayList1.add("022019");
        arrayList1.add("042019");
        arrayList1.add("062019");
        arrayList1.add("072019");

        for (int i = 0; i <= NUMBER_TO_GENERATE; i++) {
            Configvalues configvalues = Configvalues.builder()
                    .configID(i)
                    .configName(arrayList.get(i))
                    .configDate(arrayList1.get(i))
                    .build();
        configvaluesRepository.save(configvalues);
        }
    }

}