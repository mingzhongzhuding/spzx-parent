package com.pingchang.spzx.manager.service;

import com.pingchang.spzx.model.dto.system.AssignMenuDto;

import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  15:17
 */
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssignMenuDto assignMenuDto);
}
