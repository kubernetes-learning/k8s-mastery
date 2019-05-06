package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(getSaApiUrl() + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
    }

    private String getSaApiUrl() {
        Map<String, String> envList = System.getenv();
        String saHost = envList.get("SA_LOGIC_SERVICE_HOST");
        String saPort = envList.get("SA_LOGIC_SERVICE_PORT");
        return String.format("http://%s:%s", saHost, saPort);
    }

    @GetMapping("/health")
    public void testHealth() {
    }



    @GetMapping("/envs")
    public Map<String, String> getEnvs() {
        return System.getenv();
    }
}


