package com.clickhouseservice.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSetMetaData;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
@Slf4j
public class ClickHouseRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String FIND_ALL = "SELECT * FROM %s WHERE ";

    private String buildFilteredQuery(String tableName, Map<String, String> filters) {
        StringBuilder sqlQuery = new StringBuilder(String.format(FIND_ALL, tableName));
        AtomicInteger index = new AtomicInteger(0);
        filters.forEach((key, value) -> {
            if (index.getAndIncrement() > 0) {
                sqlQuery.append("OR ");
            }
            sqlQuery.append(key).append(" = ? ");
        });
        return sqlQuery.toString();
    }

    public List<Map<String, Object>> findAll(String tableName, Map<String, String> filters) {
        var filteredQuery = buildFilteredQuery(tableName, filters);
        List<Map<String, Object>> results = new ArrayList<>();
        jdbcTemplate.query(filteredQuery, filters.values().toArray(), rs -> {
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
