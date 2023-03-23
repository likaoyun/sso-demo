package com.supos.eco.service.impl;

import com.supos.eco.service.UserService;
import com.supos.eco.vo.response.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private DataSource dataSource;

    private JdbcTemplate getTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Override
    public List<Map<String,Object>> getAllUser() {
        List<Map<String,Object>> list=getTemplate().queryForList("select r.id,r.phone,r.nick_name from tb_user r");
        return list;
    }
}
