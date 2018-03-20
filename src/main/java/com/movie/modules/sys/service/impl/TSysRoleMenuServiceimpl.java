package com.movie.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.modules.sys.dao.TSysRoleMenuDao;
import com.movie.modules.sys.entity.TSysRoleMenuEntity;
import com.movie.modules.sys.service.TSysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TSysRoleMenuServiceimpl extends ServiceImpl<TSysRoleMenuDao,TSysRoleMenuEntity> implements TSysRoleMenuService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        List<TSysRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
        for(Long menuId : menuIdList){
            TSysRoleMenuEntity sysRoleMenuEntity = new TSysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }
}
