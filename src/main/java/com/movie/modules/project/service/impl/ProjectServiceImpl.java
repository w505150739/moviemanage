package com.movie.modules.project.service.impl;

import com.movie.modules.project.form.ApprovalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;

import com.movie.modules.project.dao.ProjectDao;
import com.movie.modules.project.entity.ProjectEntity;
import com.movie.modules.project.service.ProjectService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


@Service("projectService")
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProjectEntity> page = this.selectPage(
                new Query<ProjectEntity>(params).getPage(),
                new EntityWrapper<ProjectEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int updateContent(Map<String, Object> params) {
        return projectDao.updateContent(params);
    }

    @Override
    public List<ProjectEntity> queryList(Map<String, Object> params) {
        return projectDao.queryList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return projectDao.queryTotal(map);
    }

    @Override
    public int approvalPro(ApprovalForm approvalForm) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",approvalForm.getId());
        params.put("remark",approvalForm.getRemark());
        params.put("examineStatus",approvalForm.getExamineStatus());
        return projectDao.approvalPro(params);
    }

    @Override
    public int updateByMap(Map<String, Object> params) {
        return projectDao.updateByMap(params);
    }

}
