package com.movie.modules.project.controller;

import com.movie.common.base.BaseController;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.common.utils.R;
import com.movie.common.validator.ValidatorUtils;
import com.movie.modules.project.entity.ProjectEntity;
import com.movie.modules.project.form.ApprovalForm;
import com.movie.modules.project.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
@RestController
@RequestMapping("project")
public class ProjectController extends BaseController{
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:project:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);

        List<ProjectEntity> proList = this.projectService.queryList(query);

        int total = projectService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(proList, total, query.getLimit(), query.getCurrPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 待审核列表
     */
    @RequestMapping("/approvallist")
    @RequiresPermissions("project:project:approvallist")
    public R approvallist(@RequestParam Map<String, Object> params){
        params.put("examineStatus",2);
        Query query = new Query(params);

        List<ProjectEntity> proList = this.projectService.queryList(query);

        int total = projectService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(proList, total, query.getLimit(), query.getCurrPage());

        return R.ok().put("page", pageUtil);
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
        project.setPublishStatus(0);
		project.setExamineStatus(1);
        projectService.insert(project);

        return R.ok().put("id",project.getId());
    }

    /**
     * 检查项目是否可修改
     * @param id
     * @return
     */
    @RequestMapping("/checkUpdate")
    public R checkUpdate(@RequestBody Long id){
        ProjectEntity projectEntity = projectService.selectById(id);
        Integer status = projectEntity.getExamineStatus();
        Integer publishStatus = projectEntity.getPublishStatus();
        if(status != 1 && status != 3){
            return R.error("项目已在审核流程，不允许修改与删除！");
        }
        if(publishStatus != 0){
            return R.error("项目已发布，不允许修改与删除！");
        }
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
     * 提审
     * @param approvalForm
     * @return
     */
    @RequestMapping("/approval")
    @RequiresPermissions("project:project:approval")
    public R approval(@RequestBody ApprovalForm approvalForm){

        ValidatorUtils.validateEntity(approvalForm);
        ProjectEntity projectEntity = projectService.selectById(approvalForm.getId());
        if(projectEntity != null){
            Integer status = projectEntity.getExamineStatus();
            if(status != 1 && status != 3){
                return R.error("项目不能重复提审！");
            }
        }
        projectService.approvalPro(approvalForm);
        return R.ok();
    }

    /**
     * 查询 项目是否可审核
     * @param id
     * @return
     */
    @RequestMapping("/checkExamine")
    public R checkExamine(@RequestBody Long id){
        ProjectEntity projectEntity = projectService.selectById(id);
        Integer status = projectEntity.getExamineStatus();
        if(status != 2){
            return R.error("项目不允许审核！");
        }else{
            return R.ok();
        }
    }

    /**
     * 审核
     * @param approvalForm
     * @return
     */
    @RequestMapping("/examine")
    @RequiresPermissions("project:project:examine")
    public R examine(@RequestBody ApprovalForm approvalForm){

        ValidatorUtils.validateEntity(approvalForm);
        projectService.approvalPro(approvalForm);
        return R.ok();
    }

    /**
     * 发布
     * @param id
     * @return
     */
    @RequestMapping("/publish")
    @RequiresPermissions("project:project:publish")
    public R publishPro(@RequestBody Long id){
        ProjectEntity projectEntity = projectService.selectById(id);
        Integer status = projectEntity.getExamineStatus();
        Integer publishStatus = projectEntity.getPublishStatus();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        if(status != 4){
            return R.error("项目未经过审核，不允许发布/下架！");
        }else{
            if(publishStatus == 1){
                params.put("publish",0);
            }else{
                params.put("publish",1);
            }
            projectService.updateByMap(params);
            return R.ok();
        }
    }

    /**
     * 更新内容字段
     * @param request
     * @return
     */
    @RequestMapping("/updateContent")
    public R updateContent(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        projectService.updateContent(params);
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
