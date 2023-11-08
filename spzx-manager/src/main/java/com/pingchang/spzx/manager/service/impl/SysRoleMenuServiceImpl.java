package com.pingchang.spzx.manager.service.impl;

import com.pingchang.spzx.manager.mapper.SysRoleMenuMapper;
import com.pingchang.spzx.manager.service.SysMenuService;
import com.pingchang.spzx.manager.service.SysRoleMenuService;
import com.pingchang.spzx.model.dto.system.AssignMenuDto;
import com.pingchang.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  15:18
 */
@Service
public class SysRoleMenuServiceImpl  implements SysRoleMenuService {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        // 查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes() ;

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId) ;

        // 将数据存储到Map中进行返回
        Map<String , Object> result = new HashMap<>() ;
        result.put("sysMenuList" , sysMenuList) ;
        result.put("roleMenuIds" , roleMenuIds) ;

        // 返回
        return result;
    }

    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {

        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        // 获取菜单的id
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if(menuInfo != null && !menuInfo.isEmpty()) {
            sysRoleMenuMapper.doAssign(assignMenuDto) ;
        }
    }
}
