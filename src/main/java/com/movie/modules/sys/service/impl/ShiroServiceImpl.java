package com.movie.modules.sys.service.impl;

import com.movie.modules.sys.dao.TSysUserDao;
import com.movie.modules.sys.dao.TSysUserTokenDao;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.entity.TSysUserTokenEntity;
import com.movie.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroServiceImpl implements ShiroService {
//    @Autowired
//    private SysMenuDao sysMenuDao;
    @Autowired
    private TSysUserDao sysUserDao;
    @Autowired
    private TSysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
//        if(userId == GlobalContants.SUPER_ADMIN){
//            List<TSysMenuEntity> menuList = sysMenuDao.selectList(null);
//            permsList = new ArrayList<>(menuList.size());
//            for(TSysMenuEntity menu : menuList){
//                permsList.add(menu.getPerms());
//            }
//        }else{
//            permsList = sysUserDao.queryAllPerms(userId);
//        }
        permsList = sysUserDao.queryAllPerms(userId);
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public TSysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public TSysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
