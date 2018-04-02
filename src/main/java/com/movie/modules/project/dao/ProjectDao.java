package com.movie.modules.project.dao;

import com.movie.modules.project.entity.ProjectEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {
	
}
