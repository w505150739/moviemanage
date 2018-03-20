package com.movie.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.GlobalContants;
import com.movie.common.utils.MapUtils;
import com.movie.modules.sys.dao.TSysMenuDao;
import com.movie.modules.sys.entity.TSysMenuEntity;
import com.movie.modules.sys.service.TSysMenuService;
import com.movie.modules.sys.service.TSysRoleMenuService;
import com.movie.modules.sys.service.TSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TSysMenuServiceImpl extends ServiceImpl<TSysMenuDao,TSysMenuEntity> implements TSysMenuService {

    @Autowired
    private TSysUserService sysUserService;
    @Autowired
    private TSysRoleMenuService sysRoleMenuService;

    @Override
    public List<TSysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<TSysMenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<TSysMenuEntity> userMenuList = new ArrayList<>();
        for(TSysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<TSysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<TSysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<TSysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == GlobalContants.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId){
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    /**
     * 获取所有菜单列表
     */
    private List<TSysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<TSysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<TSysMenuEntity> getMenuTreeList(List<TSysMenuEntity> menuList, List<Long> menuIdList){
        List<TSysMenuEntity> subMenuList = new ArrayList<TSysMenuEntity>();

        for(TSysMenuEntity entity : menuList){
            //目录
            if(entity.getType() == GlobalContants.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
