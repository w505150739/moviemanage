package com.movie.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-24 18:52:11
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
