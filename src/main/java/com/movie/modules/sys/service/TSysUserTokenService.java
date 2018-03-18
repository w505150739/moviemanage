package com.movie.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.base.ResultData;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.entity.TSysUserTokenEntity;

public interface TSysUserTokenService extends IService<TSysUserTokenEntity> {

    /**
     * 生成token
     * @param user  用户
     */
    ResultData createToken(TSysUserEntity user);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}
