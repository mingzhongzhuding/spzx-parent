package com.pingchang.spzx.manager.service;

import com.pingchang.spzx.model.entity.system.SysMenu;
import com.pingchang.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  10:23
 */
public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    List<SysMenuVo> findMenusByUserId();
}
