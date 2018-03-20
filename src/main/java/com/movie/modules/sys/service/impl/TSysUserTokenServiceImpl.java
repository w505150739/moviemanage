package com.movie.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.base.ResultData;
import com.movie.common.config.DeployUtil;
import com.movie.common.shiro.TokenGenerator;
import com.movie.modules.sys.dao.TSysUserTokenDao;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.entity.TSysUserTokenEntity;
import com.movie.modules.sys.service.TSysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TSysUserTokenServiceImpl extends ServiceImpl<TSysUserTokenDao,TSysUserTokenEntity> implements TSysUserTokenService {

    @Autowired
    private DeployUtil deployUtil;

    @Override
    public ResultData createToken(TSysUserEntity user) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
//        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        Date expireTime = new Date(now.getTime() + deployUtil.getExpiretime());

        //判断是否生成过token
        TSysUserTokenEntity tokenEntity = this.selectById(user.getUserId());
        if(tokenEntity == null){
            tokenEntity = new TSysUserTokenEntity();
            tokenEntity.setUserId(user.getUserId());
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.insert(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("token", token);
        obj.put("expire", deployUtil.getExpiretime());
        return new ResultData(obj);
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        TSysUserTokenEntity tokenEntity = new TSysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
