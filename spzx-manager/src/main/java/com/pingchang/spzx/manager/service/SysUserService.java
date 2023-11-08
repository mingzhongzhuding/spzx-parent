package com.pingchang.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.model.dto.system.AssignRoleDto;
import com.pingchang.spzx.model.dto.system.LoginDto;
import com.pingchang.spzx.model.dto.system.SysUserDto;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.system.LoginVo;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/18  16:04
 */
public interface SysUserService {

    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void sysUserService(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer userId);

    void doAssign(AssignRoleDto assignRoleDto);
}
