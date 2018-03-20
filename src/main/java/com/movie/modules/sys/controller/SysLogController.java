package com.movie.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.common.base.BaseController;

import com.movie.modules.sys.entity.SysLogEntity;
import com.movie.modules.sys.service.SysLogService;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.R;



/**
 * 系统日志
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-21 00:13:28
 */
@RestController
@RequestMapping("sys/syslog")
public class SysLogController extends BaseController{
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:syslog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:syslog:info")
    public R info(@PathVariable("id") Long id){
			SysLogEntity sysLog = sysLogService.selectById(id);

        return R.ok().put("sysLog", sysLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:syslog:save")
    public R save(@RequestBody SysLogEntity sysLog){
			sysLogService.insert(sysLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:syslog:update")
    public R update(@RequestBody SysLogEntity sysLog){
			sysLogService.updateById(sysLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:syslog:delete")
    public R delete(@RequestBody Long[] ids){
			sysLogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
