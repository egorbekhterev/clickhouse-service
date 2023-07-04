package com.clickhouseservice.controller;

import com.clickhouseservice.service.ClickHouseService;
import com.clickhouseservice.view.EntityDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/fmba_database_report")
@AllArgsConstructor
@Slf4j
public class ClickHouseController {

    private ClickHouseService clickHouseService;

    @GetMapping
    @ResponseBody
    public EntityDto findAll(@RequestParam String tableName, @RequestParam String filter) {
        log.info("Find all data in a chosen table.");
        return clickHouseService.findAll(tableName, filter);
    }
}
