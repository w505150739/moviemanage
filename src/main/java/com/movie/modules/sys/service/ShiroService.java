package com.movie.modules.sys.service;

import com.movie.modules.sys.entity.TSysUserTokenEntity;
import com.movie.modules.sys.entity.TSysUserEntity;

import java.util.Set;

/**
 * shiro相关接口
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-17 22:04:53
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    TSysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    TSysUserEntity queryUser(Long userId);
}
