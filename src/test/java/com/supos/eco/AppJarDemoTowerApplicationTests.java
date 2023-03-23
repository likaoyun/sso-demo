package com.supos.eco;

import cn.hutool.http.Method;
import com.bluetron.eco.sdk.api.SuposApi;
import com.bluetron.eco.sdk.api.SuposApiEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AppJarDemoTowerApplicationTests {

    @Resource
    private DataSource dataSource;


    @Test
    void contextLoads() {
    }

    @Test
    public void getWithPathTest() {
        String username = "admin";
        //获取用户详情 /open-api/auth/v2/users/{username}
        String apiUrl = "/open-api/auth/v2/users/%s";
        SuposApi.doRequest(new SuposApiEntity(apiUrl, Method.GET), null, username);

        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);


        List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from table ");
        System.out.println(list.size());
    }

}
