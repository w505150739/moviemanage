package com.movie.modules.project.dao;

import com.movie.modules.project.entity.ProjectEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {

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

    int approvalPro(Map<String, Object> params);

    int updateByMap(Map<String, Object> params);
}
