package com.movie.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.exception.GlobalException;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.common.validator.Assert;
import com.movie.modules.member.dao.MemberDao;
import com.movie.modules.member.entity.MemberEntity;
import com.movie.modules.member.service.MemberService;
import com.movie.modules.web.form.WebLoginForm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberEntity> page = this.selectPage(
                new Query<MemberEntity>(params).getPage(),
                new EntityWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Override
    public MemberEntity queryByMobile(String userName) {
        MemberEntity userEntity = new MemberEntity();
        userEntity.setUserName(userName);
        return baseMapper.selectOne(userEntity);
    }

    @Override
    public long login(WebLoginForm form) {
        MemberEntity user = queryByMobile(form.getUserName());
        Assert.isNull(user, "用户名或密码错误");

        //密码错误
        if(!user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())){
            throw new GlobalException("手机号或密码错误");
        }

        return user.getId();
    }

    @Override
    public List<MemberEntity> queryList(Map<String, Object> params) {
        return this.memberDao.queryList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return this.memberDao.queryTotal(map);
    }

}
