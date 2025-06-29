package net.bi4vmr.tool.java.common.base;

/**
 * 文本处理工具。
 *
 * @author BI4VMR@outlook.com
 */
public class TextUtil {

    /**
     * 判断字符串内容是否为空。
     * <p>
     * 字符串对象为空或其内容为空，都视为空字符串。
     *
     * @param s 待测试的字符串。
     * @return "true"表示字符串为空，"false"表示字符串非空。
     */
    public static boolean isEmpty(String s) {
        return (s == null) || (s.isEmpty());
    }

    /**
     * 判断字符串内容是否非空。
     * <p>
     * 字符串对象为空或其内容为空，都视为空字符串。
     *
     * @param s 待测试的字符串。
     * @return "true"表示字符串非空，"false"表示字符串为空。
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
