package com.supos.eco.service;

import com.supos.eco.vo.response.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String,Object>> getAllUser();

    /**
     * 新增user数据
     * @param user
     */
    void saveUser(User user);

    Boolean deleteById(String id);
}
