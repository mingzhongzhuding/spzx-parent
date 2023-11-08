package com.pingchang.spzx;

import com.pingchang.spzx.model.entity.system.SysUser;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/30  23:16
 */
public class AuthContextUtil {

    //创建Threadlocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    //添加数据
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }
    //获取数据
    public static SysUser get() {
        return threadLocal.get();
    }
    //删除数据
    public static void remove() {
        threadLocal.remove();
    }
}
