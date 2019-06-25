package com.att;

/**
 * <h1>java.com.att.BootstrapTest</h1>
 * Description :
 *
 * @author gcanter
 * on 2019-06-24
 */


import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BootstrapTest {

    private static final String API_ROOT = "http://localhost:9000";


    // test to see if connection to http://localhost:9000 is working and returns all data
    @Test
    public void whenGetAllConfigs_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    // Test to see if app returns just 1 single record











}
