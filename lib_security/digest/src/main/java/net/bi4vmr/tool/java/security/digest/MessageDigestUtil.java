package net.bi4vmr.tool.java.security.digest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Locale;

/**
 * 消息摘要工具。
 *
 * @author BI4VMR@outlook.com
 */
public class MessageDigestUtil {

    /*
     * ----- 输入参数为数组 -----
     */

    /**
     * 获取消息摘要。
     *
     * @param algo 算法，请选择 {@link DigestAlgos} 中的枚举常量。
     * @param data 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getMessageDigest(DigestAlgos algo, byte[] data) {
        String result = "";

        try {
            // 获取消息摘要工具实例
            MessageDigest md = MessageDigest.getInstance(algo.getStandardName());
            // 获取消息摘要内容
            byte[] bytes = md.digest(data);
            // 将消息摘要转为16进制表示
            StringBuilder builder = new StringBuilder();
            for (byte num : bytes) {
                // 将数值与 `0x100` 进行或运算后，统一为 `1` 开头的三位数，便于后续步骤进行截取。
                String hex = Integer.toHexString((num & 0xFF) | 0x100);
                builder.append(hex, 1, 3);
            }
            result = builder.toString().toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            // 通常不会执行至此处，因为该方法只接受JDK支持的算法。
            System.err.println("Get message digest failed! Reason:[" + e.getMessage() + "]");
        }

        return result;
    }

    /**
     * 获取MD5消息摘要。
     *
     * @param data 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getMD5(byte[] data) {
        return getMessageDigest(DigestAlgos.MD5, data);
    }

    /**
     * 获取SHA-1消息摘要。
     *
     * @param data 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getSHA1(byte[] data) {
        return getMessageDigest(DigestAlgos.SHA_1, data);
    }

    /**
     * 获取SHA-256消息摘要。
     *
     * @param data 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getSHA256(byte[] data) {
        return getMessageDigest(DigestAlgos.SHA_256, data);
    }

    /*
     * ----- 输入参数为文本 -----
     */

    /**
     * 获取消息摘要。
     * <p>
     * 将以UTF-8编码将输入的字符串转换为二进制数据。
     *
     * @param algo 算法，请选择 {@link DigestAlgos} 中的枚举常量。
     * @param text 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getMessageDigest(DigestAlgos algo, String text) {
        return getMessageDigest(algo, text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取MD5消息摘要。
     *
     * @param text 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getMD5(String text) {
        return getMessageDigest(DigestAlgos.MD5, text);
    }

    /**
     * 获取SHA-1消息摘要。
     *
     * @param text 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getSHA1(String text) {
        return getMessageDigest(DigestAlgos.SHA_1, text);
    }

    /**
     * 获取SHA-256消息摘要。
     *
     * @param text 原始数据。
     * @return 消息摘要（非空，罕见的情况下可能返回空字符串）。
     */
    public static String getSHA256(String text) {
        return getMessageDigest(DigestAlgos.SHA_256, text);
    }

    /*
     * ----- 输入参数为文件 -----
     */

    /**
     * 获取消息摘要。
     * <p>
     * 采用分批读取文件内容的方法进行计算，防止文件过大时导致内存溢出。
     *
     * @param algo 算法，请选择 {@link DigestAlgos} 中的枚举常量。
     * @param file 目标文件。
     * @return 消息摘要（非空，文件不存在/不可读等情况下将返回空字符串）。
     */
    public static String getMessageDigest(DigestAlgos algo, File file) {
        String result = "";

        try (
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
        ) {
            // 获取消息摘要工具实例
            MessageDigest md = MessageDigest.getInstance(algo.getStandardName());
            // 文件缓冲区默认8M
            byte[] buffer = new byte[8 * 1024 * 1024];
            while (true) {
                int count = bis.read(buffer);
                if (count != -1) {
                    // 追加数据，更新计算结果。
                    md.update(buffer, 0, count);
                } else {
                    break;
                }
            }

            // 获取消息摘要内容
            byte[] bytes = md.digest();
            // 将消息摘要转为16进制表示
            StringBuilder builder = new StringBuilder();
            for (byte num : bytes) {
                // 将数值与 `0x100` 进行或运算后，统一为 `1` 开头的三位数，便于后续步骤进行截取。
                String hex = Integer.toHexString((num & 0xFF) | 0x100);
                builder.append(hex, 1, 3);
            }
            result = builder.toString().toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            // 通常不会执行至此处，因为该方法只接受JDK支持的算法。
            System.err.println("Get message digest failed! Reason:[" + e.getMessage() + "]");
        }

        return result;
    }

    /**
     * 获取MD5消息摘要。
     * <p>
     * 采用分批读取文件内容的方法进行计算，防止文件过大时导致内存溢出。
     *
     * @param file 目标文件。
     * @return 消息摘要（非空，文件不存在/不可读等情况下将返回空字符串）。
     */
    public static String getMD5(File file) {
        return getMessageDigest(DigestAlgos.MD5, file);
    }

    /**
     * 获取SHA-1消息摘要。
     * <p>
     * 采用分批读取文件内容的方法进行计算，防止文件过大时导致内存溢出。
     *
     * @param file 目标文件。
     * @return 消息摘要（非空，文件不存在/不可读等情况下将返回空字符串）。
     */
    public static String getSHA1(File file) {
        return getMessageDigest(DigestAlgos.SHA_1, file);
    }

    /**
     * 获取SHA-256消息摘要。
     * <p>
     * 采用分批读取文件内容的方法进行计算，防止文件过大时导致内存溢出。
     *
     * @param file 目标文件。
     * @return 消息摘要（非空，文件不存在/不可读等情况下将返回空字符串）。
     */
    public static String getSHA256(File file) {
        return getMessageDigest(DigestAlgos.SHA_256, file);
    }
}
