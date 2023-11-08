package com.pingchang.spzx.manager.controller;

import com.pingchang.spzx.AuthContextUtil;
import com.pingchang.spzx.manager.service.SysMenuService;
import com.pingchang.spzx.manager.service.SysUserService;
import com.pingchang.spzx.manager.service.ValidateCodeService;
import com.pingchang.spzx.model.dto.system.LoginDto;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import com.pingchang.spzx.model.vo.system.LoginVo;
import com.pingchang.spzx.model.vo.system.SysMenuVo;
import com.pingchang.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "首页接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取验证码
     *
     * @return
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();

        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo() {

        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

  /*  @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {

        SysUser sysUser = sysUserService.getUserInfo(token);
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }*/

    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {

        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }



    /**
     * 用户退出
     *
     * @param token
     * @return
     */
    @Operation(summary = "用户退出")
    @Parameters(value = {
            @Parameter(name = "令牌参数", required = true)
    })
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {

        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    //查询用户可以操作菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

}