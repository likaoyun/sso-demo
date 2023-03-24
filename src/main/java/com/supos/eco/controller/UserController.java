package com.supos.eco.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.api.SuposApi;
import com.bluetron.eco.sdk.api.SuposApiEnum;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.supos.eco.service.UserService;
import com.supos.eco.vo.PageResult;
import com.supos.eco.vo.Pagination;
import com.supos.eco.vo.RestResult;
import com.supos.eco.vo.request.UserSearch;
import com.supos.eco.vo.response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public PageResult<User> list(UserSearch param) {
        if (StrUtil.isBlank(param.getCompanyCode())) {
            param.setCompanyCode("default_org_company");
        }
        ApiResponse response = SuposApi.doRequest(SuposApiEnum.USER_LISTS, JSONUtil.toJsonStr(param));
        JSONObject res = JSONUtil.parseObj(response.getResponseBody());
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPagination(res.getBean("pagination", Pagination.class));
        pageResult.setList(res.getBeanList("list", User.class));
        return pageResult;
    }

    @GetMapping("/test")
    public RestResult test(){
        RestResult res=new RestResult();
        res.setCode(200L);
        res.setMsg("调用成功！");
        return res;
    }

    @GetMapping("/all")
    public List<Map<String,Object>> list(){
        return userService.getAllUser();
    }

    @PostMapping("/save")
    public Boolean save(@RequestBody User user){
        userService.saveUser(user);
        return true;
    }
    @PostMapping("/del/{id}")
    public Boolean delete(@PathVariable("id") String id){
        return userService.deleteById(id);
    }
}
