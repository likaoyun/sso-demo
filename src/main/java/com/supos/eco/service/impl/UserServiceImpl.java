package com.supos.eco.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.supos.eco.service.UserService;
import com.supos.eco.vo.response.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        List<Map<String,Object>> list=getTemplate().queryForList("select * from user r");
        return list;
    }

    @Override
    public void saveUser(User user) {
        String id= RandomUtil.randomNumbers(10);
        getTemplate().update("insert into user values(?,?,?,?,?,?,?,?,?)",
                id,user.getUsername(),user.getUserDesc(),user.getAccountType(),user.getLockStatus(),user.getPersonCode(),user.getPersonName(),
        user.getCreateTime(),user.getModifyTime());
    }

    @Override
    public Boolean deleteById(String id) {
        int res=getTemplate().update("delete from user where id=?",id);
        return res>0?true:false;
    }
}
