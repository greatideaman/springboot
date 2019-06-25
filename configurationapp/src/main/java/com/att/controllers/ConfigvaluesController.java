package com.att.controllers;

import com.att.repositories.ConfigvaluesRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.att.model.Configvalues;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ConfigvaluesController {

    private ConfigvaluesRepository configvaluesRepository;

    public ConfigvaluesController(ConfigvaluesRepository configvaluesRepository) {
        this.configvaluesRepository = configvaluesRepository;
    }

    // SHOW ALL VALUES
    @RequestMapping(value = "/api/configvalues", method = RequestMethod.GET)
    public DataTablesOutput<Configvalues> list(@Valid DataTablesInput input) {
//        configvaluesRepository.deleteAllByConfigDate("102019");
//        System.out.println(" DELETION IN AFFECT ");
        return configvaluesRepository.findAll(input);
    }

    // DELETION OF CONFIGS BY CONFIG DATE VALUE
    @Transactional // REQUIRED by Spring or will get Entity errors
    @RequestMapping(value = "/delete/{ConfigDate}", method = RequestMethod.POST)
    public RedirectView deleteConfig(@RequestParam String ConfigDate) {
        configvaluesRepository.deleteAllByConfigDate(ConfigDate);
        System.out.println(" ########## DELETION IN AFFECT FOR " + ConfigDate.toString() + "  ############## ");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(("http://localhost:9000/index.html"));
        return redirectView;
    }

    // ADDITION OF A CONFIG
    @Transactional // REQUIRED by Spring or will get Entity errors
    @RequestMapping(value = "/add/{ConfigDate1}/{ConfigName1}", method = RequestMethod.POST)
    public RedirectView addConfig(@RequestParam String ConfigDate1, String ConfigName1) {
        int newID = (((int) configvaluesRepository.count()) + 1);
        Configvalues configvalues = Configvalues.builder()
                .configID(newID)
                .configName(ConfigName1)
                .configDate(ConfigDate1)
                .build();
        configvaluesRepository.save(configvalues);

        System.out.println(" ########## ADDED FOR " + configvalues.toString() + "  ############## ");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(("http://localhost:9000/index.html"));
        return redirectView;

    }

    // DELETION OF A SINGLE CONFIG BY CONFIG ID VALUE
    @Transactional // REQUIRED by Spring or will get Entity errors
    @RequestMapping(value = "/deleteSingle/{ConfigID}", method = RequestMethod.POST)
    public RedirectView deleteSingleConfig(@RequestParam Integer ConfigID) {
        configvaluesRepository.deleteById(ConfigID);
        System.out.println(" ########## *SINGLE* DELETION IN AFFECT FOR " + ConfigID.toString() + "  ############## ");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(("http://localhost:9000/index.html"));
        return redirectView;
    }
}
