package com.pingchang.spzx.manager.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.common.exception.GuiguException;
import com.pingchang.spzx.manager.mapper.SysRoleUserMapper;
import com.pingchang.spzx.manager.mapper.SysUserMapper;
import com.pingchang.spzx.manager.service.SysUserService;
import com.pingchang.spzx.model.dto.system.AssignRoleDto;
import com.pingchang.spzx.model.dto.system.LoginDto;
import com.pingchang.spzx.model.dto.system.SysUserDto;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import com.pingchang.spzx.model.vo.system.LoginVo;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/18  16:04
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {


        String codeKey = loginDto.getCodeKey();
        String captcha = loginDto.getCaptcha();

        String redisCode = (String) redisTemplate.opsForValue().get("user:validate" + codeKey);
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(captcha, redisCode)) {
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        redisTemplate.delete("user:validate" + codeKey);

        //1 获取提交用户名，loginDto获取到
        String userName = loginDto.getUserName();
        //2 根据用户名查询数据库表sys_user
        SysUser sysUser = sysUserMapper.selectUserInfoByUsername(userName);
        //3 如果根据用户名，查询不到用户，返回错误信息
        if (sysUser == null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //4 如果根据用户名查到用户信息，用户存在
        //5 获取输入的密码，比较输入的密码和数据库密码是否一致
        String input_password = loginDto.getPassword();
        String password = sysUser.getPassword();
        input_password = DigestUtils.md5DigestAsHex(input_password.getBytes());
        //6 如果密码一致，登录成功，如果密码不一致，登录失败
        if (!Objects.equals(input_password, password)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //7 登录成功，生成用户唯一标识token
        String token = IdUtil.simpleUUID();


        //8 把登录成功用户信息放到redis中
        redisTemplate.opsForValue().set("user:login" + token, JSON.toJSONString(sysUser), 7, TimeUnit.DAYS);

        //9 返回loginVo对象

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String jsonStr = (String) redisTemplate.opsForValue().get("user:login" + token);
        SysUser sysUser = JSONObject.parseObject(jsonStr, SysUser.class);


        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum, pageSize);


        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void sysUserService(SysUser sysUser) {
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUsername(userName);
        if (dbSysUser != null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        String md5_password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(md5_password);
        //设置status值  1 可用  0 不可用
        sysUser.setStatus(1);

        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Integer userId) {
        sysUserMapper.deleteById(userId);
    }

    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {
        sysRoleUserMapper.deleteByUserId(assignRoleDto.getUserId());

        List<Long> roleIdList = assignRoleDto.getRoleIdList();
        roleIdList.forEach(roleId -> sysRoleUserMapper.doAssign(assignRoleDto.getUserId(), roleId));

    }
}
