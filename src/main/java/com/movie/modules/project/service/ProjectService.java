package com.movie.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.utils.PageUtils;
import com.movie.modules.project.entity.ProjectEntity;
import com.movie.modules.project.form.ApprovalForm;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * 项目表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
public interface ProjectService extends IService<ProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int updateContent(Map<String,Object> params);

    /**
     * 根据条件查询数据
     * @param params
     * @return
     */
    List<ProjectEntity> queryList(Map<String, Object> params);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    int approvalPro(ApprovalForm approvalForm);

    int updateByMap(Map<String, Object> params);
}

