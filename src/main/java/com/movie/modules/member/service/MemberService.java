package com.movie.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.utils.PageUtils;
import com.movie.modules.member.entity.MemberEntity;
import com.movie.modules.web.form.WebLoginForm;

import java.util.Map;

/**
 * 
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-24 18:52:11
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    MemberEntity queryByMobile(String userName);

    /**
     * web 端登录
     * @param form
     * @return 返回用户id
     */
    long login(WebLoginForm form);
}

