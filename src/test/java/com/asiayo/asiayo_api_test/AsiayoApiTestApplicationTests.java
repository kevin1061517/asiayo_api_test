package com.asiayo.asiayo_api_test;

import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(JUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AsiayoApiTestApplicationTests {
    @Autowired private MockMvc mvc;

    @Test
    void contextLoads() throws Exception {
        System.out.println("contextLoads");
    }


    @Test
    void testExchangeRateApi() throws Exception {
        String uri = "/exchangerate/calculate?source=USD&target=JPY&amount=$1,525";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String message = result.getResponse().getContentAsString();
        Assertions.assertEquals("{\"msg\":\"success\",\"amount\":\"$170496.53\"}", message);
        System.out.println(message);
    }
}
