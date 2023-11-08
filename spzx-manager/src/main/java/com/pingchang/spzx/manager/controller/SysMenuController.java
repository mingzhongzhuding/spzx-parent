package com.pingchang.spzx.manager.controller;

import com.pingchang.spzx.manager.service.SysMenuService;
import com.pingchang.spzx.model.entity.system.SysMenu;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  10:14
 */
@RestController
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    //菜单添加
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //菜单添加
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
