package net.bi4vmr.tool.java.common.base.general;

/**
 * 语法扩展工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class LanguageUtil {

    private LanguageUtil() {
        // 静态工具类，无需实例化。
    }


    /*
     * ----- 数据类型转换 -----
     */

    /**
     * 将布尔值转为整型值。
     * <p>
     * C 语言风格的通用数值转换，`true` 对应 `1`；`false` 对应 `0`。
     *
     * @param input 布尔值。
     * @return 数值。
     */
    public static int booleanToInt(boolean input) {
        return input ? 1 : 0;
    }

    /**
     * 将布尔值转为长整型值。
     * <p>
     * C 语言风格的通用数值转换，`true` 对应 `1`；`false` 对应 `0`。
     *
     * @param input 布尔值。
     * @return 数值。
     */
    public static long booleanToLong(boolean input) {
        return booleanToInt(input);
    }

    /**
     * 将布尔值转为短整型值。
     * <p>
     * C 语言风格的通用数值转换，`true` 对应 `1`；`false` 对应 `0`。
     *
     * @param input 布尔值。
     * @return 数值。
     */
    public static short booleanToShort(boolean input) {
        return (short) booleanToInt(input);
    }

    /**
     * 将布尔值转为字节型值。
     * <p>
     * C 语言风格的通用数值转换，`true` 对应 `1`；`false` 对应 `0`。
     *
     * @param input 布尔值。
     * @return 数值。
     */
    public static byte booleanToByte(boolean input) {
        return (byte) booleanToInt(input);
    }

    /**
     * 将整型值转为布尔值。
     * <p>
     * C 语言风格的通用数值转换，非 `0` 对应 `true`；`0` 对应 `false`。
     *
     * @param input 输入值。
     * @return 布尔值。
     */
    public static boolean intToBoolean(int input) {
        return input == 1;
    }

    /**
     * 将长整型值转为布尔值。
     * <p>
     * C 语言风格的通用数值转换，非 `0` 对应 `true`；`0` 对应 `false`。
     *
     * @param input 输入值。
     * @return 布尔值。
     */
    public static boolean longToBoolean(long input) {
        return intToBoolean((int) input);
    }

    /**
     * 将短整型值转为布尔值。
     * <p>
     * C 语言风格的通用数值转换，非 `0` 对应 `true`；`0` 对应 `false`。
     *
     * @param input 输入值。
     * @return 布尔值。
     */
    public static boolean shortToBoolean(short input) {
        return intToBoolean(input);
    }

    /**
     * 将字节型值转为布尔值。
     * <p>
     * C 语言风格的通用数值转换，非 `0` 对应 `true`；`0` 对应 `false`。
     *
     * @param input 输入值。
     * @return 布尔值。
     */
    public static boolean byteToBoolean(byte input) {
        return intToBoolean(input);
    }
}
