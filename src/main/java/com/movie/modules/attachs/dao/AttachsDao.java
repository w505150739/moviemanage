package com.movie.modules.attachs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.attachs.entity.AttachsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件记录表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-12 02:08:33
 */
@Mapper
public interface AttachsDao extends BaseMapper<AttachsEntity> {
	
}
