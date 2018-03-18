package com.movie.modules.sys.controller;

import com.movie.common.base.BaseController;
import com.movie.common.base.ResultData;
import com.movie.modules.sys.entity.TSysUserEntity;
import com.movie.modules.sys.service.TSysUserService;
import com.movie.modules.sys.service.TSysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public ResultData login(HttpServletRequest request)throws IOException {
        ResultData result = new ResultData();
        Map<String,Object> params = this.getAllParams(request);

        //用户信息
        TSysUserEntity user = sysUserService.queryByUserName(params.get("userName").toString());

        String password = params.get("password").toString();
        String dataPassword = user.getPassword();
        String jia = new Sha256Hash(params.get("password"), user.getSalt()).toHex();

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(params.get("password"), user.getSalt()).toHex())) {
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

}
