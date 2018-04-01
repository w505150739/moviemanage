package com.movie.modules.sys.controller;

import com.movie.common.baidu.ueditor.ActionEnter;
import com.movie.common.base.BaseController;
import com.movie.common.config.DeployUtil;
import com.movie.common.utils.oss.OssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private DeployUtil deployUtil;

    @RequestMapping(value = "/exec")
    public String exec(HttpServletRequest request, HttpServletResponse response,String action) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        logger.info("请求上传文件：" + rootPath);
        return new ActionEnter( request, rootPath,deployUtil).exec();
    }
}
