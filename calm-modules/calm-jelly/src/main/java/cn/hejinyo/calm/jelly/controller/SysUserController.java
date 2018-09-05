package cn.hejinyo.calm.jelly.controller;

import cn.hejinyo.calm.common.basis.annotation.SysLogger;
import cn.hejinyo.calm.common.basis.consts.StatusCode;
import cn.hejinyo.calm.common.basis.utils.PageInfo;
import cn.hejinyo.calm.common.basis.utils.PageQuery;
import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.common.basis.validator.RestfulValid;
import cn.hejinyo.calm.jelly.model.SysUserEntity;
import cn.hejinyo.calm.jelly.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/6/17 22:29
 */
@Api(tags = "用户管理", description = "SysUserController")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户信息
     */
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "int")
    @GetMapping("/{userId}")
    @RequiresPermissions("sys:user:view")
    public Result info(@PathVariable("userId") Integer userId) {
        SysUserEntity user = sysUserService.findOne(userId);
        if (user != null) {
            return Result.ok(user);
        }
        return Result.error(StatusCode.DATABASE_SELECT_FAILURE);
    }

    /**
     * 分页查询用户信息
     */
    @ApiOperation(value = "分页查询用户信息", notes = "支持分页，排序和高级查询")
    @RequestMapping(value = "/listPage", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions("sys:user:view1")
    public Result listPage(@RequestParam HashMap<String, Object> pageParam, @RequestBody(required = false) HashMap<String, Object> queryParam) {
        PageInfo<SysUserEntity> userPageInfo = new PageInfo<>(sysUserService.findPage(PageQuery.build(pageParam, queryParam)));
        return Result.ok(userPageInfo);
    }

    /**
     * 增加一个用户
     */
    @SysLogger("增加用户")
    @ApiOperation(value = "增加一个用户", notes = "增加一个用户")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息对象", required = true, dataType = "SysUserEntity")
    @PostMapping
    @RequiresPermissions("sys:user:save")
    public Result save(@Validated(RestfulValid.POST.class) @RequestBody SysUserEntity sysUser) {
        int result = sysUserService.save(sysUser);
        if (result > 0) {
            return Result.ok();
        }
        return Result.error(StatusCode.DATABASE_SAVE_FAILURE);
    }

    /**
     * 更新一个用户
     */
    @SysLogger("更新用户")
    @ApiOperation(value = "更新用户信息", notes = "更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "user", value = "用户详细实体", required = true, dataType = "SysUserEntity"),
            @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    })
    @PutMapping(value = "/{userId}")
    @RequiresPermissions("sys:user:update")
    public Result update(@PathVariable("userId") Integer userId, @Validated(RestfulValid.PUT.class) @RequestBody SysUserEntity sysUser) {
        int result = sysUserService.update(userId, sysUser);
        if (result > 0) {
            return Result.ok();
        }
        return Result.error(StatusCode.DATABASE_UPDATE_FAILURE);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户", notes = "删除用户：/1")
    @ApiImplicitParam(paramType = "path", name = "userIds", value = "用户ID", required = true, dataType = "int")
    @SysLogger("删除用户")
    @DeleteMapping(value = "/{userId}")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@PathVariable("userId") Integer userId) {
        int result = sysUserService.delete(userId);
        if (result > 0) {
            return Result.ok();
        }
        return Result.error(StatusCode.DATABASE_DELETE_FAILURE);
    }

}
