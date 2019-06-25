package com.att;

/**
 * <h1>java.com.att.SpringContextIntegrationTest</h1>
 * Description : Tests the Spring Context Integration to see if it loads.  I always include it in
 *                  all of my Spring Boot projects as the first test *
 * @author gcanter
 * on 2019-06-24
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextIntegrationTest {

    @Test
    public void contextLoads() {
    }
}
