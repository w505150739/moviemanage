package com.movie.modules.project.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.modules.project.entity.ProjectEntity;
import com.movie.modules.project.service.ProjectService;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.R;



/**
 * 项目表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
@RestController
@RequestMapping("project/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:project:info")
    public R info(@PathVariable("id") Long id){
			ProjectEntity project = projectService.selectById(id);

        return R.ok().put("project", project);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("project:project:save")
    public R save(@RequestBody ProjectEntity project){
		project.setExamineStatus(1);
        projectService.insert(project);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:project:update")
    public R update(@RequestBody ProjectEntity project){
			projectService.updateById(project);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:project:delete")
    public R delete(@RequestBody Long[] ids){
			projectService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
