package com.movie.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.sys.entity.TSysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-17 22:04:53
 */
@Mapper
public interface TSysUserDao extends BaseMapper<TSysUserEntity> {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    TSysUserEntity queryByUserName(String username);
}
