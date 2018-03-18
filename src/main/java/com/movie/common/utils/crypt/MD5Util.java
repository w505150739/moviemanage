package com.movie.common.utils.crypt;

/**
 * @author liuyuzhu
 * @description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2018/1/21 0:31
 */
/**
 * @Description: MD5即Message-Digest Algorithm 5（信息-摘要算法5），
 *               是一种用于产生数字签名的单项散列算法，在1991年由MIT Laboratory for Computer
 *               Science（IT计算机科学实验室）和RSA Data Security Inc（RSA数据安全公司）的Ronald L.
 *               Rivest教授开发出来， 经由MD2、MD3和MD4发展而来。
 *               Message-Digest泛指字节串(Message)的Hash变换，就是把一个任意长度的字节串变换成一定长的大整数
 *               MD5将任意长度的“字节串”变换成一个128bit的大整数，并且它是一个不可逆的字符串变换算法 在 Java
 *               中，java.security.MessageDigest 中已经定义了 MD5 的计算， 所以我们只需要简单地调用即可得到
 *               MD5 的128 位整数。 然后将此 128 位计 16 个字节转换成 16 进制表示即可
 * @Copyright: 深圳法大大网络科技有限公司 (c)2016
 * @Created Date : 2012-9-21
 * @Author jcf
 * @Vesion 1.0
 */
public class MD5Util {

    public static String getMD5(byte[] source) {
        String result = "";
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节

            for (int i = 0; i < 16; i++) {
                // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                String str = Integer.toHexString(byte0 & 0XFF);//&0XFF将除后8位，其他都置为0，这样toString时，就只显示后8位的字符串了
                if (str.length() == 1) {
                    str = "0" + str;//排除如09但str只有9
                }
                result += str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param source
     * @return
     * @Description: 将原字符串经过md5加密
     * @Created: 2012-9-21 下午4:18:51
     * @Author lys
     * @Update logs
     * @Throws Exception
     */
    public static String getMD5(String source) {
        return MD5Util.getMD5(source.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5("ww"));
        System.out.println(MD5Util.getMD5("abc".getBytes()));
        System.out.println(MD5Util.getMD5("abc".getBytes()).length());
        System.out.println(MD5Util.getMD5("a123456"));
    }
}
