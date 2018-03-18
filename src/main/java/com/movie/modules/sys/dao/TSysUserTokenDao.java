package com.movie.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.sys.entity.TSysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface TSysUserTokenDao extends BaseMapper<TSysUserTokenEntity> {

    TSysUserTokenEntity queryByToken(String token);
	
}
