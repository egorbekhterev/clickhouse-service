package com.clickhouseservice.configuration;


import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class ClickHouseConfiguration {

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}") String url) {
        try {
            return new ClickHouseDataSource(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public JdbcTemplate jdbc(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
