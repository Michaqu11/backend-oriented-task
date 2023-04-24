package com.example.taskservice.nbp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.DoubleBuffer;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class NBPController {

    @Autowired
    private NBPService nbpService;


    @GetMapping("/exchanges/{currency}/{date}")
    public ResponseEntity<Double> getAvgExchangeRangeByDate(@PathVariable("currency") String currency, @PathVariable("date") String date) {
        return ResponseEntity.ok(nbpService.getAvgExchangeRangeByDate(currency, date));
    }

    @GetMapping("/exchanges/a/{currency}/last/{count}")
    public ResponseEntity<ObjectNode> getAvgMinMaxExchangeRangeByDate(@PathVariable("currency") String currency, @PathVariable("count") String count) {
        return ResponseEntity.ok(nbpService.getAvgMinMaxExchangeRangeByDate(currency, count));
    }

    @GetMapping("/exchanges/c/{currency}/last/{count}")
    public ResponseEntity<Double> getMajorDifferenceExchangeRange(@PathVariable("currency") String currency, @PathVariable("count") String count) {
        return ResponseEntity.ok(nbpService.getMajorDifferenceExchangeRange(currency, count));
    }


}
