package com.pingchang.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.manager.mapper.SysRoleMapper;
import com.pingchang.spzx.manager.mapper.SysRoleUserMapper;
import com.pingchang.spzx.manager.service.SysRoleService;
import com.pingchang.spzx.model.dto.system.SysRoleDto;
import com.pingchang.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/1  10:57
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteSysRole(Long roleId) {

        sysRoleMapper.deleteSysRole(roleId);
    }

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {

        PageHelper.startPage(current, limit);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        return new PageInfo<>(list);
    }

    @Override
    public Map<String, Object> findAll(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findAll();

        List<Long> roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);
        Map<String , Object> map = new HashMap<>();
        map.put("allRoleList", roleList);
        map.put("sysUserRoles", roleIds);
        return map;
    }
}
