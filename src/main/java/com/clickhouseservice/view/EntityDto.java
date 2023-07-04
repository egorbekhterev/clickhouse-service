package com.clickhouseservice.view;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EntityDto {

    private List<Map<String, Object>> maps;
}
