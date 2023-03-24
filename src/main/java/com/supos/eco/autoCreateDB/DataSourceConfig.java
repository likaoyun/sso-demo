package com.supos.eco.autoCreateDB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

/**
 * @Description 创建完数据库和里面的表之后创建数据源
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    private HikariDataSource dataSource;

    @Bean(name = "dataSource")
    @DependsOn("dataSourceHelper")
    public DataSource dataSource() {
        return instance();
    }

    public HikariDataSource instance() {
        if (dataSource != null) {
            return dataSource;
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setConnectionTimeout(connectionTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setIdleTimeout(idleTimeout);

        dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
