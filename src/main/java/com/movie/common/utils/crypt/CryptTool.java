package com.movie.common.utils.crypt;

import com.movie.common.exception.GlobalException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * 加密工具类
 */
public class CryptTool {

    /** 日志 */
    private static final Logger LOGGER = Logger.getLogger(CryptTool.class);

    /** 普通key,不能修改 */
    public static String CRYPT_KEY = "YQ365123YQ365123YQ365123";

    /** 加密算法 */
    private static final String CRYPT_ALGORITHM = "DESede";

    /** 加密模式 */
    public static final int ENCRYPT_MODE = 1;

    /** 解密模式 */
    public static final int DECRYPT_MODE = 2;

    /** app客户端应用编号对应的密码 */
    public static String APP_SECRET = "YQ365123YQ365123YQ365123";

    public CryptTool() {

    }

    /**
     * <b>概要：</b> 3DES加密模式 <b>作者：</b>SUXH </br> <b>日期：</b>2015-8-5 </br>
     *
     * @param value 需要加密的字符串
     * @param key 加密秘钥
     * @return 加密后的字符串
     */
    public static String encrypt(String value, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedByte = cipher.doFinal(value.getBytes());
            String encodedByte = byte2hex(encryptedByte);
            return encodedByte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 二进制转十六进制字符串。每一个字节转为两位十六进制字符串。
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * <b>概要：</b> 调用JCE实现3DES算法 <b>作者：</b>SUXH </br> <b>日期：</b>2015-5-18 </br>
     *
     * @param mode
     *            1:加密, 2:解密
     * @param inStr
     *            要加密或者解密的数据
     * @param key
     *            24个字节的密钥
     * @return 操作成功，则返回加密或者解密后的十六进制字符串；否则，返回null
     */
    public static String tripleDES(int mode, String inStr, String key) {
        if (StringUtils.isBlank(inStr)) {
            return "";
        }
        String outStr = null;
        byte[] inBytes = null;
        if (mode == ENCRYPT_MODE) {// 加密
            try {
                inBytes = inStr.getBytes("UTF-8");
                outStr = CryptTool.byte2hex(CryptTool.encode(inBytes, "DESede", key.getBytes()));
            } catch (Exception e) {
                LOGGER.error("3des加密发生异常", e);
                throw new GlobalException("加密发生异常");
            }
        } else if (mode == DECRYPT_MODE) {// 解密
            try {
                inBytes = CryptTool.hex2byte(inStr);
                outStr = new String(CryptTool.decode(inBytes, "DESede", key.getBytes()), "UTF-8");
            } catch (Exception e) {
                LOGGER.error("3des解密发生异常", e);
                throw new GlobalException("解密发生异常");
            }
        }
        return outStr;
    }

    public static String tripleDESIOS(int mode, String inStr, String key) {
        if (StringUtils.isBlank(inStr)) {
            return "";
        }
        String outStr = null;
        byte[] inBytes = null;
        if (mode == ENCRYPT_MODE) {// 加密
            try {
                inBytes = inStr.getBytes("UTF-8");
                BASE64Encoder encoder = new BASE64Encoder();
                outStr = encoder.encode(CryptTool.encode(inBytes, "DESede", key.getBytes())).replaceAll("\r", "").replaceAll("\n", "");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                LOGGER.error("3des加密发生异常", e);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("3des加密发生异常", e);
            }
        } else if (mode == DECRYPT_MODE) {// 解密
            try {
                // --通过base64,将字符串转成byte数组
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] bytesrc = decoder.decodeBuffer(inStr);
                // --解密的key
                DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                SecretKey securekey = keyFactory.generateSecret(dks);
                // --Chipher对象解密
                Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, securekey);
                byte[] retByte = cipher.doFinal(bytesrc);
                outStr = new String(retByte, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("3des解密发生异常", e);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                LOGGER.error("输入参数错误：" + inStr);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("3des解密发生异常", e);
            }
        }
        return outStr;
    }

    /**
     * 加密
     */
    public static byte[] encode(byte[] input, String algorithm, byte[] key) {

        byte[] output = null;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, algorithm));
            output = cipher.doFinal(input);
        } catch (NoSuchAlgorithmException ex) {
            // logger.error("加密发生异常", e);
        } catch (NoSuchPaddingException ex) {
            // logger.error("加密发生异常", e);
        } catch (InvalidKeyException ex) {
            // logger.error("加密发生异常", e);
        } catch (IllegalStateException ex) {
            // logger.error("加密发生异常", e);
        } catch (IllegalBlockSizeException ex) {
            // logger.error("加密发生异常", e);
        } catch (BadPaddingException ex) {
            // logger.error("加密发生异常", e);
        }
        return output;
    }

    /**
     * 解密
     */
    public static byte[] decode(byte[] input, String algorithm, byte[] key) {
        byte[] output = null;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, algorithm));
            output = cipher.doFinal(input);
        } catch (NoSuchAlgorithmException ex) {
            // logger.error("解密发生异常", e);
        } catch (NoSuchPaddingException ex) {
            // logger.error("解密发生异常", e);
        } catch (InvalidKeyException ex) {
            // logger.error("解密发生异常", e);
        } catch (IllegalStateException ex) {
            // logger.error("解密发生异常", e);
        } catch (IllegalBlockSizeException ex) {
            // logger.error("解密发生异常", e);
        } catch (BadPaddingException ex) {
            // logger.error("解密发生异常", e);
        }
        return output;
    }

    /**
     * 十六进制字符串转二进制。两位十六进制字符串转为一个字节。
     *
     * @param hex
     * @return
     */
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    private static final int fillchar = '=';

    private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
    /**
     * ʹ��sun��˾��jce��ȫ�㷨
     */
    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * <b>概要：</b> 转换字节数组为16进制字串 如：字节数组对应的整型为52， 则其对应的16进制为：34. <b>作者：</b>suxh
     * </br> <b>日期：</b>2015-1-12 </br>
     *
     * @param b
     *            字节数组
     * @return 对应的十六进制串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * <b>概要：</b> MD5 摘要计算(String). <b>作者：</b>SUXH </br> <b>日期：</b>2015-8-21
     * </br>
     *
     * @param src
     *            需要md5加密的字节数组
     * @return 加密后的字符串
     * @throws Exception
     */
    public static byte[] md5Digest(byte[] src) {
        try {
            MessageDigest alg = MessageDigest.getInstance("MD5");
            return alg.digest(src);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("加密出错");
        }
    }

    /**
     * <b>概要：</b> MD5 摘要计算(String). <b>作者：</b>SUXH </br> <b>日期：</b>2015-8-21
     * </br>
     *
     * @param src
     *            需要md5加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String md5Digest(String src) {
        return byteArrayToHexString(md5Digest(src.getBytes()));
    }

    public static byte[] encode(byte[] data) {
        int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(cvt.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if (++i < len) {
                c |= (data[i] >> 4) & 0x0f;

            }
            ret.append(cvt.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if (++i < len) {
                    c |= (data[i] >> 6) & 0x03;

                }
                ret.append(cvt.charAt(c));
            } else {
                ++i;
                ret.append((char) fillchar);
            }

            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(cvt.charAt(c));
            } else {
                ret.append((char) fillchar);
            }
        }

        return (getBinaryBytes(ret.toString()));
    }

    /**
     * 以下代码由Base64Crypt转移过来
     */
    public static String encode2Str(byte[] data) {
        int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(cvt.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if (++i < len) {
                c |= (data[i] >> 4) & 0x0f;

            }
            ret.append(cvt.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if (++i < len) {
                    c |= (data[i] >> 6) & 0x03;

                }
                ret.append(cvt.charAt(c));
            } else {
                ++i;
                ret.append((char) fillchar);
            }

            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(cvt.charAt(c));
            } else {
                ret.append((char) fillchar);
            }
        }

        return ret.toString();
    }

    public static byte[] decode(byte[] data) {
        int c;
        int c1;
        int len = data.length;
        StringBuffer ret = new StringBuffer((len * 3) / 4);
        for (int i = 0; i < len; ++i) {
            c = cvt.indexOf(data[i]);
            ++i;
            c1 = cvt.indexOf(data[i]);
            c = ((c << 2) | ((c1 >> 4) & 0x3));
            ret.append((char) c);
            if (++i < len) {
                c = data[i];
                if (fillchar == c) {
                    break;
                }

                c = cvt.indexOf((char) c);
                c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
                ret.append((char) c1);
            }

            if (++i < len) {
                c1 = data[i];
                if (fillchar == c1) {
                    break;
                }

                c1 = cvt.indexOf((char) c1);
                c = ((c << 6) & 0xc0) | c1;
                ret.append((char) c);
            }
        }

        return (getBinaryBytes(ret.toString()));
    }

    public static String decode2Str(byte[] data) {
        int c;
        int c1;
        int len = data.length;
        StringBuffer ret = new StringBuffer((len * 3) / 4);
        for (int i = 0; i < len; ++i) {
            c = cvt.indexOf(data[i]);
            ++i;
            c1 = cvt.indexOf(data[i]);
            c = ((c << 2) | ((c1 >> 4) & 0x3));
            ret.append((char) c);
            if (++i < len) {
                c = data[i];
                if (fillchar == c) {
                    break;
                }

                c = cvt.indexOf((char) c);
                c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
                ret.append((char) c1);
            }

            if (++i < len) {
                c1 = data[i];
                if (fillchar == c1) {
                    break;
                }

                c1 = cvt.indexOf((char) c1);
                c = ((c << 6) & 0xc0) | c1;
                ret.append((char) c);
            }
        }

        return ret.toString();
    }

    public static String getString(byte[] arr) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            buf.append((char) arr[i]);
        }
        return (buf.toString());
    }

    public static byte[] getBinaryBytes(String str) {
        byte[] b = new byte[str.length()];
        for (int i = 0; i < b.length; ++i) {
            b[i] = (byte) str.charAt(i);
        }
        return (b);
    }

    /*
     * public static void main(String args[]) { String str =
     * "http://10.17.37.17:5010/singleloing/return.jsp$1154448212312"; if
     * (args.length > 0) { str = args[0]; } String temp =
     * encode2Str(str.getBytes()); String temp1 = decode2Str(temp.getBytes()); }
     */

    /**
     * 实现SHA-1消息摘要
     *
     * @param inStr
     * @return 成功，返回摘要，失败，返回null
     */
    public static String sha1(String inStr) {
        String outStr = null;
        try {
            outStr = CryptTool.digest(inStr.getBytes("UTF-8"), "SHA-1");
        } catch (UnsupportedEncodingException e) {
            // logger.error("sha1发生异常", e);
        } catch (Exception e) {
            // logger.error("sha1发生异常", e);
        }
        return outStr;
    }

    /**
     * 调用sun公司的base64编码实现
     *
     * @param mode
     *            1:加密, 2:解密
     * @param inStr
     *            要加密或者解密的数据
     * @return 操作成功，则返回加密或者解密后的字符串；否则，返回null
     */
    public static String base64(int mode, String inStr) {
        String outStr = null;
        byte[] inBytes = null;
        if (mode == ENCRYPT_MODE) {
            try {
                inBytes = inStr.getBytes("UTF-8");
                // outStr = new BASE64Encoder().encode(inBytes);
                outStr = CryptTool.encode2Str(inBytes);
            } catch (UnsupportedEncodingException e) {
                // logger.error("不支持的编码：UTF-8", e);
            } catch (Exception e) {
                // logger.error("base64加密发生异常", e);
            }
        }
        if (mode == DECRYPT_MODE) {
            try {
                outStr = new String(new BASE64Decoder().decodeBuffer(inStr), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // logger.error("不支持的编码：UTF-8");
            } catch (IOException e) {
                // logger.error("base64加密发生IO异常", e);
            } catch (Exception e) {
                // logger.error("base64解密发生异常", e);
            }
        }
        return outStr;
    }

    public static String Encrypt3Mix(String inStr, String key) {
        String authStr = CryptTool.sha1(inStr);
        authStr = CryptTool.tripleDES(CryptTool.ENCRYPT_MODE, authStr, key);
        authStr = CryptTool.base64(CryptTool.ENCRYPT_MODE, authStr);
        return authStr;
    }

    public static String base64Encode(byte b[]) {
        try {
            return (new BASE64Encoder()).encode(b);

        } catch (Exception e) {
            // logger.error("使用增强型BASE64编码格式对字节数组进行编码时出错!@" + e.getMessage());
        }
        return null;
    }

    /*
     * public static void main(String[] args) {
     * //System.out.println(Cipher.md5("88888888"));
     * System.out.println(Cipher.tripleDES(1,
     * "ZK18906057300118-18906057300-18906057300", "fstimobilefstimobilefsti"));
     * //System.out.println(
     * "中国中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@中国通信服务厦门新东创软件公司@@@"
     * .length()); }
     */

    /**
     * 以下从CipherHelper.java转移过来
     */

    /**
     * ��ϢժҪ��ʹ��algorithmժҪ�㷨��inputStr������ϢժҪ��֧��MD5��SHA-1�㷨
     *
     * @param inputBytes
     * @param algorithm
     * @return �ɹ�������ժҪ�ַ����򣬷���null
     */
    public static String digest(byte[] inputBytes, String algorithm) {
        String outputStr = null;
        try {
            MessageDigest alg = MessageDigest.getInstance(algorithm);
            alg.update(inputBytes);
            byte[] digest = alg.digest();
            outputStr = byte2hex(digest);
        } catch (NoSuchAlgorithmException ex) {
            // logger.error("û�������㷨��" + algorithm);
            // Debugger.output(ex, logger);
        }
        return outputStr;
    }

    /**
     * 以下由Digest.java转移过来
     */
    // 用MD5校验信息是否更改,返回true没有更改，false更改
    public static boolean checkMD5Digest(String myInfo, String digestInfo) {
        if (myInfo == null || digestInfo == null)
            return false;
        try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(myInfo.getBytes());
            byte[] digesta = alga.digest();
            return digestInfo.equals(byte2hex(digesta));
        } catch (NoSuchAlgorithmException ex) {
            // System.out.println("非法摘要算法");
            throw new RuntimeException();
        }
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String dataEncrypt(String str) {
        String re = "";
        if (null != str && !str.equals("")) {
            char[] ch = str.toCharArray();
            char[] newch = new char[ch.length];
            for (int i = 0; i < ch.length; i++) {
                newch[i] = (char) (ch[i] + 3);
            }
            re = new String(newch);
        }
        return re;
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String dataDecrypt(String str) {
        String re = "";
        if (null != str && !str.equals("")) {
            char[] ch = str.toCharArray();
            char[] newch = new char[ch.length];
            for (int i = 0; i < ch.length; i++) {
                newch[i] = (char) (ch[i] - 3);
            }
            re = new String(newch);
        }
        return re;
    }

    // 3DES===================================================
    /**
     * 3DES解密模式
     *
     * @param value
     * @return
     */
    public static String decrypt(String value, String key) {
        try {

            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // byte[] decodedByte = Base64.decodeBase64(value.getBytes());
            byte[] decryptedByte = cipher.doFinal(hex2byte(value));
            return new String(decryptedByte);
        } catch (Exception e) {
            return null;
        }
    }

    // 福富提供的方法
    public static byte[] base64Decode(String str) {
        try {
            byte a[] = (new BASE64Decoder()).decodeBuffer(str);
            return a;
        } catch (Exception e) {
            // logger.error("使用增强型BASE64编码格式对字符串进行解码时出错!@" + e.getMessage());
        }
        return null;
    }

    /** Test crypt */
    public static void main(String[] args) {
        try {
            String gogo = "empower" + "|" + 9;
            String crypt = CryptTool.tripleDES(CryptTool.ENCRYPT_MODE, gogo, CryptTool.CRYPT_KEY);
            System.out.println("");
//			System.out.println(CryptTool.md5Digest("1234567890"));
            /*
             * // 获得的明文数据 String desStr =
             * "MERCHANTID=3500000001&ORDERSEQ=200908310936147571&ORDERDATE=20090831&ORDERAMOUNT=1"
             * ; System.out.println("原文字符串 desStr ＝＝ " + desStr); // 生成MAC
             * String MAC = md5Digest(desStr); System.out.println("MAC == " +
             * MAC); // 使用key值生成 SIGN String keyStr = "123456";// 使用固定key //
             * 获得的明文数据 desStr =
             * "UPTRANSEQ=20080101000001&MERCHANTID=0250000001&ORDERID=2006050112564931556&PAYMENT=10000&RETNCODE=00&RETNINFO=00&PAYDATE
             * =20060101"; // 将key值和明文数据组织成一个待签名的串 desStr = desStr + "&KEY=" +
             * keyStr; System.out.println("原文字符串 desStr ＝＝ " + desStr); // 生成
             * SIGN
             */
            // String s =
            // "JTdCJTI3ZWlkX3NpZ25fYWxnb3JpdGhtJTI3JTNBJTI3MTAlMjclMkMlMjdlaWRfc2lnbl9pbmZvJTI3JTNBJTI3Rlh4TEtadTliVDRHZTF2K3pFbVJLcGF5WUkwR2FnZXh2eWVpb2tLTkx1VVp5ajREbTJMUWtIdHNNNmdHOEpNQjMyaWh2R29yUE16dXVidFN4a0Mvbm04OFh5YjZySEk0Q0dtSVBLZDlXcEg3WEkzQzhOMzV1V2lWazUwV0cwZkdhTm9yZDMyblFwYzZSSlpvcUt2U2lkcWQ4YlJVYkJoN0gzeGhnb29kWUk0JTNEJTI3JTJDJTI3ZGF0YV90b19zaWduJTI3JTNBJTI3TWpBeE5UQTBNRGd4TlRNNE16bEdORVE1TlRWQ01UZzFRak0zUTBaQ01rRTNSRUZGTlRFMlJqVTBSalEzTVRVd1lqZzVabVptTWpFME1UUmxaVFJpTW1FNU5qUTJOemMzTnpVeVptRXglMjclMkMlMjdlaWRfdXNlcl9pbmZvJTI3JTNBJTdCJTI3ZWlkaXNzdWVyJTI3JTNBJTI3ZUlEJTIwTUlDMiUyNyUyQyUyN2VpZGlzc3VlcnNuJTI3JTNBJTI3MDAwMDEwJTI3JTJDJTI3ZWlkc2MlMjclM0ElMjcxMXJRZkw3MnFuUVpBTXdrbGlPR2JnS0pRMTQ2T05hN3ZYT3FYeEhxbFB6SSUzRDAwMCUyNyUyQyUyN2VpZHNuJTI3JTNBJTI3NzZCOTAwMDAwMDAwMjM4NkZBNkRDMTlCJTI3JTJDJTI3aWRjYXJyaWVyJTI3JTNBJTI3QUFvQUFBRmkzQlUlM0QlMjclN0QlMkMlMjdpZGNhcmQlMjclM0ElMjczNjA1MDIxOTkwMDYzMDAwMTYlMjclMkMlMjduYW1lJTI3JTNBJTI3JTI3JTJDJTI3dXVpZCUyNyUzQSUyNzUwYjg5ZmZmMjE0MTRlZTRiMmE5NjQ2Nzc3NzUyZmExJTI3JTdE";
            // System.out.println(CryptTool.base64(DECRYPT_MODE, s));
            // String
            // a=CryptTool.md5Digest("000002"+"000003-2201501252120035"+"352229198405072013");
            // System.out.println(a);
            // String pdfPath =
            // "http://fabigbigpdftmptest.oss-cn-hangzhou.aliyuncs.com/de9c2e9283db4acab8f3af6bdafe209e?Expires=1438745342&OSSAccessKeyId=xeYBSJ0phvI0VxU2&Signature=1%2B60ZbTPjl2a9qxvWesK%2FuGRhjc%3D";
            // String encryptPdfPath =
            // CryptTool.tripleDES(CryptTool.ENCRYPT_MODE, pdfPath,
            // CryptTool.CRYPT_KEY);
            // System.out.println(encryptPdfPath);
            // String decryptPdfPath =
            // CryptTool.tripleDES(CryptTool.DECRYPT_MODE,
            // "5045DE9AD3AF6E3706A8415D7DA74EB4FDEFBFD71DA3CFEE0C46D959DB3614435D6EFCB14E51DC78B37049E8430E410BA1C906007A8B31368B0CE1A5A1484DD92F0DDB101E977958C72FC8F29194BA2BF9758C2227A1F6A5E9DD9611DA15DF284612A411D4AD844D",
            // CryptTool.CRYPT_KEY);
            // System.out.println(decryptPdfPath);
            // System.out.println(CryptTool.md5Digest("苏晓慧"));
            // String checkToken = CryptTool.base64(CryptTool.ENCRYPT_MODE,
            // CryptTool.sha1(1 + CryptTool.md5Digest("1") +
            // CryptTool.sha1("1")));
            //
            //
            // //System.out.println(checkToken);
            // String str ="123456";
            // String ss= CryptTool.tripleDES(CryptTool.ENCRYPT_MODE, str,
            // CryptTool.CRYPT_KEY);
            // String ss= CryptTool.base64(CryptTool.ENCRYPT_MODE,
            // CryptTool.sha1("1" + CryptTool.md5Digest("1448289300099") +
            // CryptTool.sha1("6701")));
            // String ss = CryptTool.sha1("1" +
            // CryptTool.md5Digest("1448289300099") + CryptTool.sha1("6701"));
            // System.out.println(ss);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
