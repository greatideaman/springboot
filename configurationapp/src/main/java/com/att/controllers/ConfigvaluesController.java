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
    public DataTablesOutput<Configvalues> list (@Valid DataTablesInput input) {
//        configvaluesRepository.deleteAllByConfigDate("102019");
//        System.out.println(" DELETION IN AFFECT ");
        return configvaluesRepository.findAll(input);
    }

    @Transactional
    @RequestMapping(value = "/delete/{ConfigDate}", method = RequestMethod.POST)
    public RedirectView deleteConfig (@RequestParam String ConfigDate) {
        configvaluesRepository.deleteAllByConfigDate(ConfigDate);
        System.out.println(" ########## DELETION IN AFFECT FOR " + ConfigDate.toString() + "  ############## ");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(("http://localhost:9000/index.html"));
        return redirectView;


//        // DELETE to delete a single config in a month
//    @RequestMapping(value="/api/delete/{ConfigDate}") // http://localhost:9000
//    // /configuration/delete/?ConfigDate=012019
//    public @ResponseBody String deleteConfig(@RequestParam String ConfigDate) {
//        configvaluesRepository.deleteAllByConfigDate(ConfigDate);
//        return ("Deletion of " + ConfigDate + " is complete");
    }












    // TODO CLEAN THIS UP
//    @RequestMapping(value = "/api/configvalues", method = RequestMethod.POST)
//    public DataTablesOutput<Configvalues> listPost (@Valid DataTablesInput input) {
//        return configvaluesRepository.findAll(input);
//    }

}

// TODO:  DELETE ALL THE BELOW THAT IS NOT NEEDED
//
//    @Autowired
//    private ConfigValueRepository configValueRepository;
//
//    public ConfigValueController (ConfigValueRepository configValueRepository) {
//        this.configValueRepository = configValueRepository;
//    }
//
////    // GET to show all values
////    @GetMapping  // http://localhost:9000/configuration
////    public Iterable findAll() {
////        return configValueRepository.findAll();
////    }
////
//
//
//
//    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
//    public DataTablesOutput<ConfigValue> list(@Valid DataTablesInput input) {
//        return configValueRepository.findAll(input);
//    }
//
//    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
//    public DataTablesOutput<ConfigValue> listPost(@Valid DataTablesInput input) {
//        return configValueRepository.findAll(input);
//    }
//
//
//
//
//    // GET to show all values by date
//    @GetMapping("/showall/{configDate}")  // http://localhost:9000/configuration/showall/042019
//    public List findByConfigDate(@PathVariable String configDate) {
//        return configValueRepository.findByConfigDate(configDate);
//    }
//
//    // GET to show all values by date
//    @Transactional
//    @GetMapping("/showall2/{configID}")  // http://localhost:9000/configuration/showall/3
//    public List findByConfigID(@PathVariable int configID) {
//        return configValueRepository.findByConfigID(configID);
//    }
//
//

//
//
//    // DELETE to delete all values of a month
//    @Transactional
//    @GetMapping("/deleteDate/{configDate}") //http://localhost:9000/configuration/deleteDate/042019
//    public void deleteMonth(@PathVariable String configDate) {
//        configValueRepository.deleteConfigValuesByConfigDate(configDate);
////        configValueRepository.findByConfigDate(configDate);
////        System.out.println("Found for deletion date " + configDate.toString());
//        configValueRepository.deleteConfigValuesByConfigDate(configDate);
//        System.out.println("Deleted date "+ configDate.toString());
//    }
//
//    // Add items for a time period
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ConfigValue create(@RequestBody ConfigValue configValue) {
//        //ConfigValue configValue1 = configValueRepository.save(configValue);
//        //return configValue1;
//        return configValueRepository.save(configValue);
//    }
//
////    @ResponseBody
////    public List<ConfigValue> getConfigurationsForYearMonth(
////            @PathVariable("yearMonthNumber") String yearMonth) {
////
////        return new ArrayList<>();
////    }
//
//    // DELETE to delete all values
////    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
////    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
////        try {
////
////        } catch (Exception ex) {
////
////        }
////    }
////
////    // PUT to create 1 entry/ 1 config
////    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
////    public void addConfigurationForYearMonth(
////            @PathVariable("yearMonthNumber") String yearMonth,
////            @RequestBody ConfigValue value) {
////
////    }
//}
