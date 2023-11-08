package com.pingchang.spzx.manager.service.impl;

import com.pingchang.spzx.AuthContextUtil;
import com.pingchang.spzx.manager.mapper.SysMenuMapper;
import com.pingchang.spzx.manager.mapper.SysRoleMenuMapper;
import com.pingchang.spzx.manager.service.SysMenuService;
import com.pingchang.spzx.manager.utils.MenuHelper;
import com.pingchang.spzx.model.entity.system.SysMenu;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.system.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  10:23
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenuList = sysMenuMapper.selectAll();

        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        return MenuHelper.bulidTree(sysMenuList);
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
        //新添加子菜单，把父菜单isHalf半开状态 1
        updateSysRoleMenu(sysMenu);

    }

    public void updateSysRoleMenu(SysMenu sysMenu) {
        SysMenu parentMenu =  sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if (parentMenu != null) {
            //把父菜单isHalf半开状态 1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId()) ;
            // 递归调用
            updateSysRoleMenu(parentMenu) ;
        }
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }


    @Override
    public List<SysMenuVo> findMenusByUserId() {
        SysUser sysUser = AuthContextUtil.get();
        List<SysMenu> list = sysMenuMapper.findMenusByUserId(sysUser.getId());

        List<SysMenu> sysMenuList = MenuHelper.bulidTree(list);
        return this.buildMenus(sysMenuList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

}
