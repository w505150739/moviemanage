package com.movie.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.GlobalContants;
import com.movie.common.utils.PageUtil;
import com.movie.common.utils.Query;
import com.movie.common.utils.crypt.MD5Util;
import com.movie.modules.sys.dao.TSysUserDao;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.service.TSysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("tSysUserService")
public class TSysUserServiceImpl extends ServiceImpl<TSysUserDao, TSysUserEntity> implements TSysUserService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        Page<TSysUserEntity> page = this.selectPage(
                new Query<TSysUserEntity>(params).getPage(),
                new EntityWrapper<TSysUserEntity>()
        );

        return new PageUtil(page.getTotal(),page.getRecords());
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public TSysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public void save(TSysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.insert(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(TSysUserEntity user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.deleteBatchIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        TSysUserEntity userEntity = new TSysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new EntityWrapper<TSysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(TSysUserEntity user){
        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(user.getCreateUserId() == GlobalContants.SUPER_ADMIN){
            return ;
        }

        //查询用户创建的角色列表
//        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
//        if(!roleIdList.containsAll(user.getRoleIdList())){
//            throw new GlobalException("新增用户所选角色，不是本人创建");
//        }
    }

    public static void main(String [] args){
        String password = "1343994f3aa1eec4ee8606b418a7a8a7";
        String a = new Sha256Hash(password, "YzcmCZNvbXocrsz9dm8e").toHex();
        System.out.println(a);
    }

}
