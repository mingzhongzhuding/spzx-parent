package com.pingchang.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.manager.service.SysUserService;
import com.pingchang.spzx.model.dto.system.AssignRoleDto;
import com.pingchang.spzx.model.dto.system.SysUserDto;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/2  9:05
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize, sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.sysUserService(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{userId}")
    public Result deleteSysUser(@PathVariable Integer userId) {
        sysUserService.deleteById(userId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //用户分配角色
    //保存分配数据
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleDto assignRoleDto) {
        sysUserService.doAssign(assignRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


}
