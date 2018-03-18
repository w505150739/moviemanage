package com.movie.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.utils.PageUtil;
import com.movie.modules.sys.entity.TSysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-17 22:04:53
 */
public interface TSysUserService extends IService<TSysUserEntity> {

    PageUtil queryPage(Map<String, Object> params);

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

    /**
     * 保存用户
     */
    void save(TSysUserEntity user);

    /**
     * 修改用户
     */
    void update(TSysUserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}

