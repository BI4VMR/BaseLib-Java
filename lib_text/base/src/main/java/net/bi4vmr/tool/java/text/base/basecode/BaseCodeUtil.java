package net.bi4vmr.tool.java.text.base.basecode;

import java.util.Base64;

/**
 * Base系列编码处理工具。
 *
 * @author BI4VMR@outlook.com
 */
public class BaseCodeUtil {

    /**
     * 进行Base64编码。
     * <p>
     * 使用Base64对原始数据进行编码。
     *
     * @param data 原始二进制数据。
     * @return Base64编码文本。
     */
    public static String encodeBase64(byte[] data) {
        return Base64.getMimeEncoder().encodeToString(data);
    }

    /**
     * 进行Base64解码。
     * <p>
     * 使用Base64对编码文本进行解码。
     *
     * @param text Base64编码文本。
     * @return 原始二进制数据。
     */
    public static byte[] decodeBase64(String text) {
        return Base64.getMimeDecoder().decode(text);
    }
}
