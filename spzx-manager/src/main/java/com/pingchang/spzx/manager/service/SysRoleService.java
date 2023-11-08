package com.pingchang.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.model.dto.system.SysRoleDto;
import com.pingchang.spzx.model.entity.system.SysRole;

import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/1  10:57
 */
public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Long roleId);

    Map<String, Object> findAll(Long userId);
}
