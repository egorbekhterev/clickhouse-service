package com.clickhouseservice.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSetMetaData;
import java.util.*;

@Repository
@AllArgsConstructor
@Slf4j
public class ClickHouseRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String FIND_ALL = "SELECT * FROM %s WHERE %s";

    public List<Map<String, Object>> findAll(String tableName, String filter) {
        List<Map<String, Object>> results = new ArrayList<>();
        jdbcTemplate.query(String.format(FIND_ALL, tableName, filter), rs -> {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> innerObjects = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    var columnName = rsmd.getColumnName(i);
                    var columnValue = rs.getObject(rsmd.getColumnName(i), Object.class);
                    if (columnValue == null) {
                        continue;
                    }
                    innerObjects.putIfAbsent(columnName, columnValue);
                }
                results.add(innerObjects);
            }
            return columnCount;
        });
        return results;
    }
}
