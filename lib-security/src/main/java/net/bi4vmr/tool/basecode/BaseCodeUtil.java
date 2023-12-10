package net.bi4vmr.tool.basecode;

import java.util.Base64;

/**
 * Name        : BaseCodeUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-19 23:17
 * <p>
 * Description : Base系列编解码工具。
 */
public class BaseCodeUtil {

    /**
     * Name        : 进行Base64编码
     * <p>
     * Description : 使用Base64对原始数据进行编码。
     *
     * @param data 原始二进制数据
     * @return Base64编码文本
     */
    public static String encodeBase64(byte[] data) {
        return Base64.getMimeEncoder().encodeToString(data);
    }

    /**
     * Name        : 进行Base64解码
     * <p>
     * Description : 使用Base64对编码文本进行解码。
     *
     * @param text Base64编码文本
     * @return 原始二进制数据
     */
    public static byte[] decodeBase64(String text) {
        return Base64.getMimeDecoder().decode(text);
    }
}
