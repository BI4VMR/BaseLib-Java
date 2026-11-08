package net.bi4vmr.tool.java.common.base.id;

import com.github.f4b6a3.uuid.alt.GUID;

import java.time.Instant;
import java.util.Random;

/**
 * ID 工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class IDUtil {

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


    private IDUtil() {
        // 静态工具类，无需实例化。
    }


    /**
     * 生成 GUID v4 字符串。
     *
     * @param noHyphen  移除 {@code -} 。
     * @param upperCase 转换为大写字母。
     * @param random    随机数生成器。
     * @return GUID.
     */
    public static String GUIDv4(boolean noHyphen, boolean upperCase, Random random) {
        String raw = GUID.v4(random).toString();
        return format(raw, noHyphen, upperCase);
    }

    /**
     * 生成 GUID v4 字符串。
     * <p>
     * 以 {@code ThreadLocalRandom} 作为随机数生成器。
     *
     * @param noHyphen  移除 {@code -} 。
     * @param upperCase 转换为大写字母。
     * @return GUID.
     */
    public static String GUIDv4(boolean noHyphen, boolean upperCase) {
        String raw = GUID.v4().toString();
        return format(raw, noHyphen, upperCase);
    }

    /**
     * 生成 GUID v4 字符串。
     * <p>
     * 生成无连字符且为大写字母的 ID ，以 {@code ThreadLocalRandom} 作为随机数生成器。
     *
     * @return GUID.
     */
    public static String GUIDv4() {
        return GUIDv4(true, true);
    }

    /**
     * 生成 GUID v7 字符串。
     *
     * @param noHyphen  移除 {@code -} 。
     * @param upperCase 转换为大写字母。
     * @param instant   指定时刻。
     * @param random    随机数生成器。
     * @return GUID.
     */
    public static String GUIDv7(boolean noHyphen, boolean upperCase, Instant instant, Random random) {
        String raw = GUID.v7(instant, random).toString();
        return format(raw, noHyphen, upperCase);
    }

    /**
     * 生成 GUID v7 字符串。
     * <p>
     * 以当前时刻为基准，以 {@code ThreadLocalRandom} 作为随机数生成器。
     *
     * @param noHyphen  移除 {@code -} 。
     * @param upperCase 转换为大写字母。
     * @return GUID.
     */
    public static String GUIDv7(boolean noHyphen, boolean upperCase) {
        String raw = GUID.v7().toString();
        return format(raw, noHyphen, upperCase);
    }

    /**
     * 生成 GUID v7 字符串。
     * <p>
     * 生成无连字符且为大写字母的 ID ，以当前时刻为基准，以 {@code ThreadLocalRandom} 作为随机数生成器。
     *
     * @return GUID.
     */
    public static String GUIDv7() {
        return GUIDv7(true, true);
    }

    /**
     * 格式化文本。
     *
     * @param text      输入文本。
     * @param noHyphen  移除 {@code -} 。
     * @param upperCase 转换为大写字母。
     * @return 格式化后的文本。
     */
    private static String format(String text, boolean noHyphen, boolean upperCase) {
        // 移除连字符
        if (noHyphen) {
            text = text.replace("-", "");
        }

        // 转换为大写
        if (upperCase) {
            text = text.toUpperCase();
        }

        return text;
    }
}
