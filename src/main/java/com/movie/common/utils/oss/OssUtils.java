/**
 * 封装的Oss处理方法
 * com.aliyun.openservices.oss.fabigbig
 * OssUtils.java
 * @author zyb 2015-1-7
 */
package com.movie.common.utils.oss;

import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.fadada.client.util.common.UUIDGenerator;
import com.movie.common.utils.GlobalContants;
import com.movie.common.utils.oss.impl.SrtpOssImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;

public class OssUtils {

    private static final Logger logger = Logger.getLogger(OssUtils.class);
    private static IOssOperate ossService = null;

    public static void getOssService(String ossFlag){
        if("srtp".equals(ossFlag)) {
            logger.info("==============srtp===============");
            ossService = new SrtpOssImpl();
        }
    }

    public static void main(String[] args) {
//        String uuid = "fa04567e73b443a18cbf19261a28b230_0";
//        String bucket = OssConstants.BUCKET_DOC;
//        String url = getPrivateUrlEndPoint(OssConstants.OSS_URL, bucket, uuid);
//        System.out.println("===private url===" + url);

    }

    /**
     * 生成oss私有化的访问url
     * @param endpoint   内网or公网地址
     * @param bucketName 桶名
     * @param uuid       标志
     */
    public static String getPrivateUrlEndPoint(String endpoint, String bucketName, String uuid) {
        if (null == uuid || "".equals(uuid)) {
            return "";
        }
        return ossService.getPrivateUrl(bucketName, uuid, GlobalContants.EXPIRETIME);
    }

    /**
     * 指定uuid上传文件到aliyun(如：在上传合同模版时，也上传模版图片，需指定模版图片的uuid)
     * @param filestorepath pdf存储路径
     * @param bucketname    bucket名称
     * @param contenttype   文件类型，如：application/pdf，application/msword,image/gif
     * @param uuid          uuid(aliyun的object名称)
     */
    public static String sendFileToAliyunWithUUid(String filestorepath, String bucketname
            , String contenttype, String uuid) {
        try {
            ossService.ensureBucket(bucketname);
            ossService.putObject(bucketname, uuid, filestorepath, contenttype);
        } catch (Exception e) {
            logger.error("上传文件异常", e);
            return "";
        }
        return uuid;
    }

    /**
     * 发送文件到阿里云------默认地址走互联网
     * @param file        文件实体路径如D:\\21221.jpg
     * @param bucketname  存储到阿里云的某个bucket，目前有2个，配置在deploy文件
     *                    一个存储pdf文件，OssConstants.BUCKET_PDF
     *                    一个存储其他类型文件 OssConstants.PIC_BUCKET_NAME
     * @param contenttype 文件类型，指定contenttype
     * @return uuid 唯一的编号，编号需要入库，根据该编号生成文件访问的路径
     */
    public static String sendFileToAliyun(File file, String bucketname, String contenttype) {
        String uuid = UUIDGenerator.getUUID();
        try {
            ossService.ensureBucket(bucketname);
            ossService.putObject(bucketname, uuid, file, contenttype);
        } catch (Exception e) {
            logger.error("文件上传异常");
            throw new RuntimeException(e);
        }

        return uuid;
    }

    /**
     * @param input       文件流
     * @param bucketname  桶
     * @param contenttype 文件类型
     */
    public static String sendFileToAliyun(InputStream input, String bucketname, String contenttype) {
        if (null == input) {
            return "";
        }
        String uuid = UUIDGenerator.getUUID();
        try {
            ossService.ensureBucket(bucketname);
            ossService.putObject(bucketname, uuid, input, contenttype);
        } catch (Exception e) {
            logger.error("上传文件异常", e);
            return "";
        }

        return uuid;
    }

    /**
     * 复制一个Object，返回复制后的uuid
     * @param sourceBucketName      源桶
     * @param sourceUUid            源UUID
     * @param destinationBucketName 目标桶
     * @return 目标UUID
     */
    public static String copyObject(String sourceBucketName, String sourceUUid, String destinationBucketName)
            throws Exception {
        String destinationUUid = UUIDGenerator.getUUID();
        if (StringUtils.isBlank(sourceBucketName)
                || StringUtils.isBlank(sourceUUid)
                || StringUtils.isBlank(destinationBucketName)) {
            return "";
        }
        return ossService.duplicate(sourceBucketName, sourceUUid, destinationBucketName, destinationUUid);
    }

    public static String copyObject(String sourceBucketName, String sourceUUid
            , String destinationBucketName, String destinationUUid) {
        if (StringUtils.isBlank(sourceBucketName) || StringUtils.isBlank(sourceUUid)
                || StringUtils.isBlank(destinationBucketName) || StringUtils.isBlank(destinationUUid)) {
            return "";
        }
        try {
            ossService.getObject(sourceBucketName, sourceUUid);
            return ossService.duplicate(sourceBucketName, sourceUUid, destinationBucketName, destinationUUid);
        } catch (Exception e) {
            logger.error("复制文件异常", e);
            return "";
        }
    }
    public static Long getObjectSize(String bucketName, String uuid) {
        if (null == uuid || "".equals(uuid)) {
            return 0L;
        }
        ObjectMetadata metaData = ossService.getObjectMetaData(bucketName, uuid);
        return metaData.getContentLength();
    }
}
