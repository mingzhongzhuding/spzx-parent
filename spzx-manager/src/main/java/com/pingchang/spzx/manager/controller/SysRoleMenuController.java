package com.pingchang.spzx.manager.controller;

import com.pingchang.spzx.manager.service.SysRoleMenuService;
import com.pingchang.spzx.model.dto.system.AssignMenuDto;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  15:13
 */
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService ;

    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String , Object>> findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String , Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId) ;
        return Result.build(sysRoleMenuList , ResultCodeEnum.SUCCESS) ;
    }


    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto) {
        sysRoleMenuService.doAssign(assignMenuDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
