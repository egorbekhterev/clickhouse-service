package com.clickhouseservice.service;

import com.clickhouseservice.repository.ClickHouseRepository;
import com.clickhouseservice.view.EntityDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ClickHouseService {

    private ClickHouseRepository clickHouseRepository;

    public EntityDto findAll(String tableName, Map<String, String> filters) {
        var maps = clickHouseRepository.findAll(tableName, filters);
        EntityDto entityDto = new EntityDto();
        entityDto.setMaps(maps);
        return entityDto;
    }
}
