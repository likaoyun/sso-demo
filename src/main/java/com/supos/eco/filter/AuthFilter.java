package com.supos.eco.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.supos.eco.util.CacheUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/20
 */
@Component
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Value("${app.white-list}")
    private String whiteList;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String[] excludeUris = whiteList.split(";");
        if (excludeUris != null && excludeUris.length > 0) {
            for (int i = 0; i < excludeUris.length; i++) {
                Pattern p = Pattern.compile(excludeUris[i]);
                Matcher matcher = p.matcher(request.getRequestURI());
                boolean matched = matcher.find();
                if (matched) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        String userToken = request.getHeader("userToken");
        if (StrUtil.isNotBlank(userToken)) {
            // 与缓存中的值进行比较
            if (!ObjectUtils.isEmpty(CacheUtils.get(userToken))) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        JSONObject res = new JSONObject();
        res.set("code", 401);
        res.set("msg", "无权限访问");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(res.toString());
    }
}
