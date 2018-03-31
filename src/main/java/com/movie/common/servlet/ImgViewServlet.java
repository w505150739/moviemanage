package com.movie.common.servlet;

import com.movie.common.exception.GlobalException;
import com.movie.common.utils.HttpUtil;
import com.movie.common.utils.oss.OssUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ImgViewServlet extends HttpServlet {

    private static final String TMEP_DIR = System.getProperty("java.io.tmpdir") + File.separator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            String fileName = request.getParameter("fileName");
//            String suffix = request.getParameter("suffix");//文件后缀格式
//            String fileType = request.getParameter("fileType");//文件类型
            String uuid = request.getParameter("uuid");
            String url = OssUtils.getPrivateUrlEndPoint("","imgtest",uuid);
            String filePath = TMEP_DIR + uuid + ".jpg";
            if(StringUtils.isBlank(url)) {
                throw new GlobalException("文件不存在，无法下载或查看");
            }
            boolean df = HttpUtil.downloadFile(filePath, url);
            if(!df) {
                throw new GlobalException("文件不存在，无法下载或查看");
            }
            String fileName = uuid + ".jpg";
            File dfile = new File(filePath);
            if(true) {//图片不需要下载
                String contentType = new MimetypesFileTypeMap().getContentType(dfile);
                response.setContentType(contentType);//设置文件MIME类型
                String filenamedisplay = null;
                if("IE".equals(HttpUtil.getBrowser(request))){
                    //IE下载使用这种方式
                    filenamedisplay = URLEncoder.encode(fileName, "UTF-8");
                } else {
                    //火狐、谷歌下载使用这种方式
                    filenamedisplay = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                }
                response.setCharacterEncoding("UTF-8");
                //设置Content-Disposition
                response.setHeader("Content-Disposition", "attachment;filename="+ filenamedisplay);
            }
            //读取目标文件，通过response将目标文件写到客户端
            InputStream in = new FileInputStream(dfile);
            OutputStream out = response.getOutputStream();
            //写文件
            int b;
            while((b=in.read())!= -1)
            {
                out.write(b);
            }
            in.close();
            out.close();
            dfile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
