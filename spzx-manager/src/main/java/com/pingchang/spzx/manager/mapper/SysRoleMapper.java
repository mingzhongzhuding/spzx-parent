package com.pingchang.spzx.manager.mapper;

import com.pingchang.spzx.model.dto.system.SysRoleDto;
import com.pingchang.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/1  10:58
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(Long roleId);

    List<SysRole> findAll();
}
