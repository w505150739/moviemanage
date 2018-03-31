package com.movie.common.utils.oss.impl;

import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.fadada.SrtpMetaData;
import com.fadada.client.SrtpClient;
import com.movie.common.utils.oss.IOssOperate;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SrtpOssImpl implements IOssOperate {

    private static final Logger logger = Logger.getLogger(SrtpOssImpl.class);

    public SrtpOssImpl() {
    }

    /**
     * 概要：OSSObjectSample类的构造函数
     */
    public SrtpOssImpl(String endpoint) {
    }

    @Override
    public void ensureBucket(String bucketName) throws Exception {

    }

    @Override
    public void setBucketPublicReadable(String bucketName) throws Exception {

    }

    @Override
    public void deleteBucket(String bucketName) throws Exception {

    }

    @Override
    public void putObject(String bucketName, String key, String filepath, String contentType) throws Exception {
        File file = new File(filepath);
        SrtpClient.uploadFile(file, key, bucketName);
    }

    @Override
    public void putObject(String bucketName, String key, InputStream input, String contentType) throws Exception {
        OutputStream os = null;
        try {
            // 临时文件路径
            String sysTemp = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
            File file = new File(sysTemp + key);
            os = new FileOutputStream(file);
            int bytesRead;
            int len = 8192;
            byte[] buffer = new byte[len];
            while ((bytesRead = input.read(buffer, 0, len)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            SrtpClient.uploadFile(file, key, bucketName);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                logger.error("关闭连接失败", e);
            }
        }
    }

    @Override
    public void putObject(String bucketName, String key, File file, String contentType) throws Exception {
        SrtpClient.uploadFile(file, key, bucketName);
    }

    @Override
    public OSSObject getObject(String bucketName, String key) throws Exception {
        OSSObject obj = new OSSObject();
        //创建OSS对象
        ObjectMetadata objMetadata = new ObjectMetadata();
        SrtpMetaData metaData = SrtpClient.fileMetaData(key, bucketName);
        objMetadata.setContentLength(Integer.valueOf(metaData.getContentLength()));
        objMetadata.setContentType(metaData.getContentType());
        //设置ossobject
        obj.setBucketName(bucketName);
        obj.setKey(key);
        obj.setObjectMetadata(objMetadata);
        return obj;
    }

    @Override
    public void getObject(String bucketName, String key, String filename) throws Exception {
        SrtpClient.fileDownLoadUrl(key, bucketName);
    }

    @Override
    public String getPrivateUrl(String bucketName, String key, int expires) {
        return SrtpClient.fileDownLoadUrl(key, bucketName);
    }

    @Override
    public ObjectMetadata getObjectMetaData(String bucketName, String key) {
        SrtpMetaData metaData = SrtpClient.fileMetaData(key, bucketName);
        ObjectMetadata objMetadata = new ObjectMetadata();
        objMetadata.setContentLength(Integer.valueOf(metaData.getContentLength()));
        objMetadata.setContentType(metaData.getContentType());
        return objMetadata;
    }

    @Override
    public void deleteObject(String bucketName, String key) throws Exception {
        SrtpClient.deleteFile(key, bucketName);
    }

    @Override
    public String duplicate(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) {
        return SrtpClient.copyFile(sourceKey, sourceBucketName, destinationKey, destinationBucketName);
    }
}
