package com.pingchang.spzx.manager.mapper;

import com.pingchang.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/3  15:18
 */
@Mapper
public interface SysRoleMenuMapper {
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void doAssign(AssignMenuDto assignMenuDto);

    void updateSysRoleMenuIsHalf(Long menuId);
}
