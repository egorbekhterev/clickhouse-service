package com.clickhouseservice.service;

import com.clickhouseservice.repository.ClickHouseRepository;
import com.clickhouseservice.view.EntityDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClickHouseService {

    private ClickHouseRepository clickHouseRepository;

    public EntityDto findAll(String tableName, String filter) {
        var maps = clickHouseRepository.findAll(tableName, filter);
        EntityDto entityDto = new EntityDto();
        entityDto.setMaps(maps);
        return entityDto;
    }
}
