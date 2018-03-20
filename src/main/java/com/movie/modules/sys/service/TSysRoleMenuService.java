package com.movie.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.modules.sys.entity.TSysRoleMenuEntity;

import java.util.List;

public interface TSysRoleMenuService extends IService<TSysRoleMenuEntity> {
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
