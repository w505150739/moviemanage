package com.movie.modules.web.controller;

import com.movie.common.utils.JwtUtils;
import com.movie.common.utils.R;
import com.movie.common.validator.ValidatorUtils;
import com.movie.modules.member.entity.MemberEntity;
import com.movie.modules.member.service.MemberService;
import com.movie.modules.web.form.RegisterForm;
import com.movie.modules.web.form.WebLoginForm;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WebUserController {

    private static final Logger logger = LoggerFactory.getLogger(WebUserController.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MemberService memberService;

    /**
     * web 用户登录
     * @param form
     * @return
     */
    @RequestMapping("/weblogin")
    public R login(@RequestBody WebLoginForm form){
        logger.info("web端登录请求，params：" + form.toString());
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        long userId = memberService.login(form);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }

    /**
     * 用户注册
     * @param form
     * @return
     */
    @RequestMapping("/register")
    public R register(@RequestBody RegisterForm form){
        logger.info("web端用户注册请求，params：" + form.toString());
        //表单校验
        ValidatorUtils.validateEntity(form);

        MemberEntity user = new MemberEntity();
        user.setPhone(form.getPhone());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(form.getPassword(), salt).toHex());
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setEmail(form.getEmail());
        user.setUserName(form.getUserName());
        user.setSalt(salt);
        memberService.insert(user);

        return R.ok();
    }
}
