package com.asiayo.asiayo_api_test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExchangeRateService {
    public double exchangeRateCalculate(String source, String target, String amountStr) throws Exception {
        Resource resource = new ClassPathResource("static/ExchangeRate.json");
        InputStream inputStream = resource.getInputStream();
        JsonNode jsonNode = new ObjectMapper().readTree(inputStream);


        double rate = jsonNode.get("currencies").get(source).get(target).asDouble();
        amountStr = clearCashAndComma(amountStr);
        double amount = Double.parseDouble(amountStr);
        return rate * amount;
    }

    private String clearCashAndComma(String s) {
        s = StringUtils.substring(s, 1);
        s = StringUtils.replace(s, ",", "");
        return s.trim();
    }
}
