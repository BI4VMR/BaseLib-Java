package net.bi4vmr.tool.java.security.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 消息摘要工具。
 *
 * @author BI4VMR@outlook.com
 */
public class MessageDigestUtil {

    /* 消息摘要算法名称 */
    /**
     * JDK内置算法：MD5
     */
    public static String ALGO_MD5 = "MD5";

    /**
     * JDK内置算法：SHA-1
     */
    public static String ALGO_SHA1 = "SHA";

    /**
     * JDK内置算法：SHA-256
     */
    public static String ALGO_SHA256 = "SHA-256";

    /**
     * 获取MD5消息摘要。
     *
     * @param data 原始数据。
     * @return MD5结果。
     */
    public static String getMD5(String data) {
        return getMessageDigest(ALGO_MD5, data);
    }

    /**
     * 获取SHA-1消息摘要。
     *
     * @param data 原始数据。
     * @return SHA-1结果。
     */
    public static String getSHA1(String data) {
        return getMessageDigest(ALGO_SHA1, data);
    }

    /**
     * 获取SHA-256消息摘要。
     *
     * @param data 原始数据。
     * @return SHA-256结果。
     */
    public static String getSHA256(String data) {
        return getMessageDigest(ALGO_SHA256, data);
    }

    /**
     * 获取消息摘要。
     * <p>
     * 获取消息摘要，如果传入的算法当前JVM不支持，将会返回空值。
     *
     * @param algo 摘要算法，可以填写本类中以"ALGO"开头的常量，也可以填写自定义算法名称。
     * @param data 原始数据。
     * @return 消息摘要文本，可能为空值。
     */
    public static String getMessageDigest(String algo, String data) {
        String result = null;

        try {
            // 获取消息摘要工具实例
            MessageDigest md = MessageDigest.getInstance(algo);
            // 获取消息摘要内容
            byte[] bytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
            // 将消息摘要转为16进制表示
            StringBuilder sb = new StringBuilder();
            for (byte num : bytes) {
                sb.append(Integer.toHexString((num & 0xFF) | 0x100), 1, 3);
            }
            result = sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
