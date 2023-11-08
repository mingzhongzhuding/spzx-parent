package com.pingchang.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.pingchang.spzx.AuthContextUtil;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/30  23:24
 */

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求方式
        String method = request.getMethod();

        //如果请求方式是options，预检请求，直接放行
        if ("OPTIONS".equals(method)) {
            return true;
        }
        //从请求头获取token
        String token = request.getHeader("token");
        //如果token不为空，拿着token查询redis
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        String userInfoString = (String) redisTemplate.opsForValue().get("user:login" + token);
        //如果redis查询不到数据，返回错误提示
        if (StrUtil.isEmpty(userInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }
        //如果redis查询到用户信息，把用户信息放到ThreadLocal里面
        SysUser sysUser = JSON.parseObject(userInfoString, SysUser.class);
        AuthContextUtil.set(sysUser);
        //把redis用户信息数据更新过期时间
        redisTemplate.expire("user:login" + token, 30, TimeUnit.MINUTES);
        //放行
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //ThreadLocal删除
        AuthContextUtil.remove();
    }


    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
