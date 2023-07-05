package com.clickhouseservice.controller;

import com.clickhouseservice.service.ClickHouseService;
import com.clickhouseservice.view.EntityDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/clickhouse-service")
@AllArgsConstructor
@Slf4j
public class ClickHouseController {

    private ClickHouseService clickHouseService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<EntityDto> findAll(@RequestParam String tableName, @RequestParam Map<String, String> filters) {
        log.info("Find all data in a chosen table.");
        filters.remove("tableName");
        return new ResponseEntity<>(clickHouseService.findAll(tableName, filters), HttpStatus.FOUND);
    }
}
