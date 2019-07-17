package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
    }

    @GetMapping("/health")
    public Map<String, String> testHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "web-app is running!");
        return response;
    }

    @GetMapping("/envs")
    public Map<String, String> getEnvs() {
        return System.getenv();
    }

    @GetMapping("/streaming")
    public ResponseEntity<StreamingResponseBody> getStream() {
        StreamingResponseBody stream = out -> {
            for (int i = 0; i < 5; i++) {
                String message = "Counting : " + i;
                out.write(message.getBytes());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }
}


