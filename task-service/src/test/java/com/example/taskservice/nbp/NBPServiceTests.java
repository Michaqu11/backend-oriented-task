package com.example.taskservice.nbp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NBPServiceTests {

    @Autowired
    private NBPService nbpService;

    @Test
    public void findMinMaxValueTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String newString = "[{\"mid\": \"13.123\"}, {\"mid\": \"6.4934\"}, {\"mid\": \"2.75423\"}]";
        JsonNode newNode = objectMapper.readTree(newString);
        double[] result = nbpService.findMinMaxValue(newNode);
        assertThat(result).isEqualTo(new double[]{2.75423, 13.123});

    }

    @Test
    public void findMajorDifferenceTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String newString = "[{\"ask\": \"13.123\", \"bid\": \"1.01\"}, {\"ask\": \"6.4934\", \"bid\": \"5.32\"}, {\"ask\": \"2.75423\", \"bid\": \"3.1231\"}]";
        JsonNode newNode = objectMapper.readTree(newString);
        double result = nbpService.findMajorDifference(newNode);
        assertThat(result).isEqualTo(12.113);

    }

}
