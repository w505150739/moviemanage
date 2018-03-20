package com.movie.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.modules.sys.entity.TSysMenuEntity;

import java.util.List;

public interface TSysMenuService extends IService<TSysMenuEntity>{

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<TSysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<TSysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<TSysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<TSysMenuEntity> getUserMenuList(Long userId);

    /**
     * 删除
     */
    void delete(Long menuId);
}
