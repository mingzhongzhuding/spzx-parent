package com.pingchang.spzx.manager.mapper;

import com.pingchang.spzx.model.entity.system.SysMenu;
import com.pingchang.spzx.model.entity.system.SysUser;
import com.pingchang.spzx.model.vo.system.SysMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  10:25
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectAll();

    void save(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    List<SysMenu> findMenusByUserId(Long userId);

    SysMenu  selectParentMenu(Long parentId);
}
