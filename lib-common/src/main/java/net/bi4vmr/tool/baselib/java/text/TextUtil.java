package net.bi4vmr.tool.baselib.java.text;

/**
 * Name        : TextUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@qq.com
 * <p>
 * Date        : 2022-11-20 00:01
 * <p>
 * Description : 文本处理工具类。
 */
public class TextUtil {

    /**
     * Name        : 判断字符串内容是否为空
     * <p>
     * Description : 字符串对象为空或其内容为空，都视为空字符串。
     *
     * @param s 待测试的字符串
     * @return "true"表示字符串为空，"false"表示字符串非空。
     */
    public static boolean isEmpty(String s) {
        return (s == null) || (s.isEmpty());
    }

    /**
     * Name        : 判断字符串内容是否非空
     * <p>
     * Description : 字符串对象为空或其内容为空，都视为空字符串。
     *
     * @param s 待测试的字符串
     * @return "true"表示字符串非空，"false"表示字符串为空。
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Name        : 判断字符串内容是否为数字
     * <p>
     * Description : 判断字符串内容是否为数字，只要有一个字符不是数字，则认为该字符串不是一个数字。
     *
     * @param text        待测试的字符串
     * @param signSupport 是否支持符号，设为"true"时可以判断负数字符串；设为"false"时，传入负数字符串则不认为是数字。
     * @return "true"表示字符串是数字，"false"表示字符串不是数字。
     */
    public static boolean isNumeric(String text, boolean signSupport) {
        if (isEmpty(text)) {
            return false;
        }

        // 截取无符号数字
        String textUnsign;
        if (text.charAt(0) == '-') {
            if (signSupport) {
                textUnsign = text.substring(1);
            } else {
                return false;
            }
        } else {
            textUnsign = text;
        }

        // 按位判断字符串是否为数字
        for (int i = 0; i < textUnsign.length(); i++) {
            char c = textUnsign.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Name        : 判断字符串内容是否为数字
     * <p>
     * Description : 判断字符串内容是否为数字，支持负数字符串。"-100"、"2834"这种格式的字符串可以通过测试。
     *
     * @param text 待测试的字符串
     * @return "true"表示字符串是数字，"false"表示字符串不是数字。
     */
    public static boolean isNumeric(String text) {
        return isNumeric(text, true);
    }

    /**
     * Name        : 获取序号
     * <p>
     * Description : 在数字前添加"0"，生成序号。
     *
     * @param num    原始数值，必须大于0。
     * @param length 序号的位数
     * @return 序号，参数不合法时将返回Null。
     */
    public static String getSerialNumber(int num, int length) {
        if (num <= 0 || length <= 0) {
            return null;
        }

        return String.format("%0" + length + "d", num);
    }

    /**
     * Name        : 获取序号
     * <p>
     * Description : 在数字前添加"0"，生成序号。
     *
     * @param text   原始字符串，格式必须为数字且值大于0。
     * @param length 序号的位数
     * @return 序号，参数不合法时将返回Null。
     */
    public static String getSerialNumber(String text, int length) {
        if (!isNumeric(text, false)) {
            return null;
        }

        return getSerialNumber(Integer.parseInt(text), length);
    }
}
