package com.pingchang.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.manager.service.SysRoleService;
import com.pingchang.spzx.model.dto.system.SysRoleDto;
import com.pingchang.spzx.model.dto.system.SysUserDto;
import com.pingchang.spzx.model.entity.system.SysRole;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/1  10:56
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteSysRole(@PathVariable Long roleId) {
        sysRoleService.deleteSysRole(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //查询所有角色
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable Long userId) {
        Map<String,Object> map =  sysRoleService.findAll(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
}
