package com.asiayo.asiayo_api_test.controller;

import com.asiayo.asiayo_api_test.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExchangeRateController {
    @Autowired private ExchangeRateService exchangeRateService;

    @GetMapping(value = "exchangerate/calculate")
    public Map<String, String> exchangeRateCalculate(
            @RequestParam("source") String source,
            @RequestParam("target") String target,
            @RequestParam("amount") String amount) throws Exception {
        double finalAmount = exchangeRateService.exchangeRateCalculate(source, target, amount);

        Map<String, String> result = new HashMap<>();
        result.put("msg", "success");
        result.put("amount", "$" + String.format("%.2f", finalAmount));

        return result;
    }
}
