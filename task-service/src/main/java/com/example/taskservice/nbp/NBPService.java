package com.example.taskservice.nbp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class NBPService {


    public double[] findMinMaxValue(JsonNode json) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;

        for (JsonNode element : json) {
            double value = element.get("mid").asDouble();
            if (min > value) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }

        return new double[]{min, max};
    }

    public double findMajorDifference(JsonNode json) {
        double major_difference = 0;

        for (JsonNode element : json) {
            double ask = element.get("ask").asDouble();
            double bid = element.get("bid").asDouble();
            double difference = ask-bid;
            if(difference > major_difference){
                major_difference = difference;
            }
        }

        return major_difference;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JsonNode readDataFromNBP(String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String result = readAll(rd);
            return objectMapper.readTree(result);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public double getAvgExchangeRangeByDate(String currency, String date) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json", currency, date);
        double result = 0;

        JsonNode jsonNode = readDataFromNBP(url);
        if (jsonNode != null)
            result = jsonNode.get("rates").get(0).get("mid").asDouble();

        return result;
    }

    public ObjectNode getAvgMinMaxExchangeRangeByDate(String currency, String count) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%s/?format=json", currency, count);
        JsonNode jsonNode = readDataFromNBP(url);
        double min = 0, max = 0;
        if (jsonNode != null) {
            JsonNode node = jsonNode.get("rates");
            double[] result = findMinMaxValue(node);
            min = result[0];
            max = result[1];
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode result = objectMapper.createObjectNode();
        result.put("min", min);
        result.put("max", max);

        return result;
    }
    public Double getMajorDifferenceExchangeRange(String currency, String count) {
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%s/?format=json", currency, count);
        JsonNode jsonNode = readDataFromNBP(url);
        double difference = 0;
        if (jsonNode != null) {
            JsonNode node = jsonNode.get("rates");
            difference = findMajorDifference(node);
        }

        return difference;
    }
}
