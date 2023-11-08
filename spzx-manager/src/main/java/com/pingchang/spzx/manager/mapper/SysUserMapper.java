package com.pingchang.spzx.manager.mapper;

import com.pingchang.spzx.model.dto.system.SysUserDto;
import com.pingchang.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/18  16:06
 */
@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUsername(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);



    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer userId);
}
