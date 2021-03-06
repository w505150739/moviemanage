package com.movie.modules.sys.controller;

import com.movie.common.base.BaseController;
import com.movie.common.utils.R;
import com.movie.modules.sys.entity.SysUserEntity;
import com.movie.modules.sys.form.SysLoginForm;
import com.movie.modules.sys.service.SysUserService;
import com.movie.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018年3月10日 下午1:15:31
 */
@RestController
public class SysLoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {

		logger.info("登录请求参数：username：" + form.getUsername() + "^_^ password:" + form.getPassword());

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
}
