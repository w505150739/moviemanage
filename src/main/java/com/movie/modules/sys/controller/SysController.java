package com.movie.modules.sys.controller;

import com.movie.common.base.BaseController;
import com.movie.common.base.ResultData;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.form.LoginForm;
import com.movie.modules.sys.service.TSysUserService;
import com.movie.modules.sys.service.TSysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SysController extends BaseController {

    @Autowired
    private TSysUserService sysUserService;

    @Autowired
    private TSysUserTokenService userTokenService;
    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public ResultData login(@RequestBody @Valid LoginForm loginForm, BindingResult validResult, HttpServletRequest request)throws IOException {
        ResultData result = new ResultData();
        /** 检验参数 **/
        if (validResult.hasErrors()) {
            List<ObjectError> errors = validResult.getAllErrors();
            for (ObjectError error : errors) {
                return result.errorResult(error.getDefaultMessage());
            }
        }

        //用户信息
        TSysUserEntity user = sysUserService.queryByUserName(loginForm.getUsername());

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(loginForm.getPassword(), user.getSalt()).toHex())) {
            return result.errorResult("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return result.errorResult("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        result = userTokenService.createToken(user);
        return result;
    }

    public static void main(String[] args) {
        String salt = "YzcmCZNvbXocrsz9dm8e";
        String password = "admin";
        System.out.print(new Sha256Hash(password, salt).toHex());
    }

}
