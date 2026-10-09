package net.bi4vmr.tool.java.common.base;

import java.util.UUID;

/**
 * 标识符工具类。
 *
 * @author BI4VMR@outlook.com
 */
public class IdentityUtil {

    /**
     * 无效 ID 。
     * <p>
     * 用于表示未初始化的 ID 变量，可在不希望出现空值的场景使用。
     */
    public static final String ID_INVALID = "0";

    /**
     * 无效 UUID 。
     * <p>
     * 用于表示未初始化的 UUID 变量，可在不希望出现空值的场景使用。
     */
    public static final String UUID_INVALID = "00000000-0000-0000-0000-000000000000";

    /**
     * 无效 UUID （无分隔符）。
     * <p>
     * 用于表示未初始化的 UUID 变量，可在不希望出现空值的场景使用。
     */
    public static final String UUID_NL_INVALID = "00000000000000000000000000000000";


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
