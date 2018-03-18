package com.movie.modules.sys.controller;

import com.movie.common.base.ResultData;
import com.movie.common.utils.PageUtil;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.service.TSysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;



/**
 * 系统用户
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-17 22:04:53
 */
@RestController
@RequestMapping("sys/tsysuser")
public class TSysUserController {
    @Autowired
    private TSysUserService tSysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tsysuser:list")
    public ResultData list(@RequestParam Map<String, Object> params){
        PageUtil page = tSysUserService.queryPage(params);

        return new ResultData(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:tsysuser:info")
    public ResultData info(@PathVariable("userId") Long userId){

        TSysUserEntity tSysUser = tSysUserService.selectById(userId);

        return new ResultData(tSysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tsysuser:save")
    public ResultData save(@RequestBody TSysUserEntity tSysUser){

        tSysUserService.insert(tSysUser);

        return new ResultData();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tsysuser:update")
    public ResultData update(@RequestBody TSysUserEntity tSysUser){

        tSysUserService.updateById(tSysUser);

        return new ResultData();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tsysuser:delete")
    public ResultData delete(@RequestBody Long[] userIds){

        tSysUserService.deleteBatchIds(Arrays.asList(userIds));

        return new ResultData();
    }

}
