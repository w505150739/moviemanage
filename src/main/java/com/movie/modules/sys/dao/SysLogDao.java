package com.movie.modules.sys.dao;

import com.movie.modules.sys.entity.SysLogEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-21 00:13:28
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
