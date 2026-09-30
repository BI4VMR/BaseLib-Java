package net.bi4vmr.tool.java.common.base;

import java.util.UUID;

/**
 * 标识符工具类。
 *
 * @author BI4VMR@outlook.com
 */
public class IdentityUtil {

    /**
     * 生成UUID。
     *
     * @return UUID文本
     */
    public static String genUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成不含分隔符的UUID。
     *
     * @return UUID文本。
     */
    public static String genUUIDPure() {
        return genUUID().replace("-", "");
    }

    /**
     * 生成UUID（大写字母）.
     *
     * @return UUID文本。
     */
    public static String genUUIDUpper() {
        return genUUID().toUpperCase();
    }

    /**
     * 生成不含分隔符的UUID（大写字母）。
     *
     * @return UUID文本。
     */
    public static String genUUIDPureUpper() {
        return genUUIDPure().toUpperCase();
    }
}
