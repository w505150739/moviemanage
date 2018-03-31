package com.movie.common.utils.oss;

import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.ObjectMetadata;

import java.io.File;
import java.io.InputStream;

/**
 * 定义对象存储服务标准操作
 */
public interface IOssOperate {

    /**
     * 创建Bucket.
     */
    void ensureBucket(String bucketName) throws Exception;

    /**
     * 把Bucket设置为所有人可读
     */
    void setBucketPublicReadable(String bucketName) throws Exception;

    /**
     * 删除一个Bucket和其中的Objects
     */
    void deleteBucket(String bucketName) throws Exception;

    /**
     * 上传文件
     */
    void putObject(String bucketName, String key, String filepath, String contentType)
            throws Exception;

    /**
     * 上传对象
     */
    void putObject(String bucketName, String key, InputStream input, String contentType)
            throws Exception;

    /**
     * 上传文件到阿里云
     */
    void putObject(String bucketName, String key, File file, String contentType)
            throws Exception;

    /**
     * 获取一个对象
     */
    OSSObject getObject(String bucketName, String key) throws Exception;

    /**
     * 下载文件
     */
    void getObject(String bucketName, String key, String filename) throws Exception;

    /**
     * 生成私有化的访问url
     */
    String getPrivateUrl(String bucketName, String key, int expires);

    /**
     * 获取ObjectMetadata
     */
    ObjectMetadata getObjectMetaData(String bucketName, String key);

    /**
     * 删除对象
     */
    void deleteObject(String bucketName, String key) throws Exception;

    /**
     * 复制一份文件
     */
    String duplicate(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey);

}
