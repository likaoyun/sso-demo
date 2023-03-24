package com.supos.eco.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.api.SuposApi;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.supos.eco.util.CacheUtils;
import com.supos.eco.vo.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 前后端分离时跳转入口
 *
 * @author caonuoqi@supos.com
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class RedirectController {


    /**
     * 应用的退出接口
     */
    @Value("${app.logout}")
    private String logout;

    @RequestMapping("/authorize")
    public void auto(String redirect_url, HttpServletResponse response) throws UnsupportedEncodingException {
        String location = SuposApi.authService.authorize(redirect_url, "1");
        log.info("重定向地址：{}", location);
        response.addHeader("Location", location);
        response.setStatus(HttpStatus.FOUND.value());
        return;
    }

    /**
     * 获取token
     * @param code
     * @param logoutUri
     * @return
     */
    @GetMapping("/getToken")
    public RestResult getToken(String code, String logoutUri) {
        ApiResponse apiResponse = SuposApi.authService.accessToken(code, logoutUri);
        JSONObject result = JSONUtil.parseObj(apiResponse.getResponseBody());
        String userToken = result.getStr("accessToken");
        return new RestResult(0L, "Success", userToken);
    }

    @GetMapping("accessToken")
    @ResponseBody
    public RestResult accessToken(String code) {
        String token = UUID.randomUUID().toString().replace("-", "");
        ApiResponse apiResponse = SuposApi.authService.accessToken(code, String.format(logout, token));
        if (apiResponse.getHttpCode() == 200) {
            JSONObject result = JSONUtil.parseObj(apiResponse.getResponseBody());
            //登录用户名
            String username = result.getStr("username");
            //关联的人员编码
            String personCode = result.getStr("personCode");
            //人员所属公司编码
            String companyCode = result.getStr("companyCode");
            String accessToken = result.getStr("accessToken");
            String refreshToken = result.getStr("refreshToken");
            //TODO 将supOS用户与应用用户关联
            //TODO 应用自身的权限业务,此处相当于应用原来的登录成功后操作
            //TODO 将accessToken缓存 demo为了减少中间件依赖 使用 hutool Cache 应用请使用redis等分布式缓存
            CacheUtils.set(token, apiResponse.getResponseBody());
        }
        return new RestResult(0L, "Success", token);
    }

    @GetMapping("/logout/{tokenKey}")
    public RestResult logout(@PathVariable String tokenKey) {
        log.info("应用退出......");
        CacheUtils.delete(tokenKey);
        return RestResult.success();
    }
}
