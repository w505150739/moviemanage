package com.movie.modules.sys.controller;

import com.movie.common.baidu.ueditor.ActionEnter;
import com.movie.common.baidu.ueditor.define.FileType;
import com.movie.common.base.BaseController;
import com.movie.common.config.DeployUtil;
import com.movie.common.utils.GlobalContants;
import com.movie.common.utils.R;
import com.movie.common.utils.oss.OssUtils;
import com.movie.modules.attachs.entity.AttachsEntity;
import com.movie.modules.attachs.service.AttachsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private DeployUtil deployUtil;

    @Autowired
    private AttachsService attachsService;

    @RequestMapping(value = "/exec")
    public String exec(HttpServletRequest request, HttpServletResponse response,String action) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        logger.info("请求上传文件：" + rootPath);
        return new ActionEnter( request, rootPath,deployUtil).exec();
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadfile")
    public R uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = this.getAllParams(request);
        String uuid = "";
        logger.info("文件上传参数" + params.toString());
        try {
            if(file != null){
                InputStream is = file.getInputStream();
                OssUtils.getOssService(deployUtil.getOssflag());
                uuid = OssUtils.sendFileToAliyun(is,deployUtil.getImgbucket(), FileType.getSuffixByFilename(params.get("name").toString()));
            }
            /***
             * uuid 不为空 附件表存入数据
             */
            if(StringUtils.isNotBlank(uuid)){
                GlobalContants.AttachType.NEWS.getValue();
                AttachsEntity attachsEntity = new AttachsEntity();
                attachsEntity.setFileSize(Long.parseLong(params.get("size").toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error("上传失败");
        }
        return R.ok().put("uuid",uuid);
    }
}
