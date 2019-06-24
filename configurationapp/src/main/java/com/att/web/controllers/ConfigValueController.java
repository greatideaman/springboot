package com.att.web.controllers;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import com.att.repositories.ConfigValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/configuration")
public class ConfigValueController {

    //private ConfigurationDao dao;
    private ConfigValueRepository configValueRepository;


    public ConfigValueController (ConfigValueRepository configValueRepository) {
        this.configValueRepository = configValueRepository;
    }

//    @Autowired
//    public ConfigValueController(ConfigurationDao dao) {
//        this.dao = dao;
//    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET)
    public String getConfigValues(Model model) {
        model.addAttribute("configValues", configValueRepository.findAll());

        return "index";
    }
//    @ResponseBody
//    public List<ConfigValue> getConfigurationsForYearMonth(
//            @PathVariable("yearMonthNumber") String yearMonth) {
//
//        return new ArrayList<>();
//    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {

        } catch (Exception ex) {

        }
    }

    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {

    }
}
