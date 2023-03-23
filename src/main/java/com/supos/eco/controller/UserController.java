package com.supos.eco.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.api.SuposApi;
import com.bluetron.eco.sdk.api.SuposApiEnum;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.supos.eco.vo.PageResult;
import com.supos.eco.vo.Pagination;
import com.supos.eco.vo.request.UserSearch;
import com.supos.eco.vo.response.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/user")
public class UserController {
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
}
