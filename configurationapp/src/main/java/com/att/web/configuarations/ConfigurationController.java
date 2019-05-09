package com.att.web.configuarations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;
import com.att.model.configurations.AjaxResponseBody;
import com.att.model.configurations.Cache;
import com.att.model.configurations.CacheImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@SessionAttributes("Cache")
public class ConfigurationController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
    private ConfigurationDao dao;
	
	private Cache cache;
	
	public ConfigurationController() {
		
	}

//    public ConfigurationController(ConfigurationDao dao) {
//        this.dao = dao;
//    }
    
    @GetMapping("/getConfigurations")
     public ResponseEntity<AjaxResponseBody> getConfigurationForYearMonth( HttpServletRequest request) {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String monthYear = request.getParameter("monthYear");
    	cache = getCache();
    	
    	List<ConfigValue> configList = dao.getConfigurations(cache, monthYear);
    	
    	String json = "";
    	if (configList != null) {
    		json = jsonArray2String(configList);
    	}
    	
    	AjaxResponseBody result = new AjaxResponseBody();
     	result.setData(json);
      	
        return ResponseEntity.ok(result);
    }

    
    @PostMapping("/addConfigurations")
    public ResponseEntity<?> addConfigurationForYearMonth(@Validated @RequestBody  String json, Errors errors) {
    	cache = getCache();
    	
    	List<ConfigValue> configList = convertJsonString(json);
    	
 		for (ConfigValue config: configList) {
			cache = dao.addConfiguration(cache, config);
	    	setCache(cache);
 		}
    	
    	return ResponseEntity.ok("200");
    }
    
    
    @PostMapping("/deleteConfigurations")
    public ResponseEntity<?>  deleteConfigurationForYearMonth(@Validated @RequestBody String json, Errors errors) {
    	cache = getCache();
    	
    	List<ConfigValue> configList = convertJsonString(json);
    	
    	for (ConfigValue config: configList) {
    		cache = dao.deleteConfiguration(cache, config);
	    	setCache(cache);
    	}
    	
    	return ResponseEntity.ok("200");
    }

    
    private Cache getCache() {
    	Cache cache = null;
    	
    	if (request != null) {
	    	HttpSession session = request.getSession();
	    	cache = (Cache) session.getAttribute("cache");
	    	if (cache == null) {
	    		cache = new CacheImpl();
	    		session.setAttribute("cache", cache);
	    	}
    	}
    	return cache;
    }
    
    
    private void setCache(Cache cache) {
    	HttpSession session = request.getSession();
    	session.setAttribute("cache", cache);
    }
    
    
    private  String jsonArray2String(List<ConfigValue> configList) {
    	ObjectMapper mapper = new ObjectMapper();    	
    	StringBuilder json = new StringBuilder();
    	
    	for (ConfigValue config : configList) {
    		try {
    			if (json.length() == 0 ) {
    				json.append(mapper.writeValueAsString(config));
    			}
    			else {
    				json.append(",").append(mapper.writeValueAsString(config));
    			}
     			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
    	}
    	
    	return "[" + json.toString() + "]";
    }
    
    
    private List<ConfigValue> convertJsonString(String json) {
    	List<ConfigValue> cvList = new ArrayList<ConfigValue>();
    	
    	String[] tokens = json.split(",\"");
    	for (String token: tokens) {
    		int i = token.indexOf("{")-1;
    		token = token.substring(token.indexOf(":\"") + 2);
    		ObjectMapper mapper = new ObjectMapper();
    		try {
    			
				ConfigValue value = mapper.readValue(token.replace("\\\"", "\""), ConfigValue.class);
				cvList.add(value);
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    	return cvList;
    }
    
}
