package net.bi4vmr.tool.baselib.java.identity;

import java.util.UUID;

/**
 * Name        : IdentityUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-19 23:17
 * <p>
 * Description : 标识符工具类。
 */
public class IdentityUtil {

    /**
     * Name        : 生成UUID
     * <p>
     * Description : 生成UUID。
     *
     * @return UUID文本
     */
    public static String genUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Name        : 生成不含分隔符的UUID
     * <p>
     * Description : 生成不含分隔符的UUID。
     *
     * @return UUID文本
     */
    public static String genUUIDPure() {
        return genUUID().replace("-", "");
    }

    /**
     * Name        : 生成UUID（大写字母）
     * <p>
     * Description : 生成UUID，字母为大写。
     *
     * @return UUID文本
     */
    public static String genUUIDUpper() {
        return genUUID().toUpperCase();
    }

    /**
     * Name        : 生成不含分隔符的UUID（大写字母）
     * <p>
     * Description : 生成不含分隔符的UUID，字母为大写。
     *
     * @return UUID文本
     */
    public static String genUUIDPureUpper() {
        return genUUIDPure().toUpperCase();
    }
}
