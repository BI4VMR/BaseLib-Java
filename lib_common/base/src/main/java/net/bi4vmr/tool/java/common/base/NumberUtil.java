package net.bi4vmr.tool.java.common.base;

/**
 * 数字处理工具。
 *
 * @author BI4VMR@outlook.com
 */
public class NumberUtil {

    /*
     * ----- 数字与文本转换 -----
     */

    /**
     * 判断文本内容是否为整数。
     * <p>
     * 默认不支持正负号，若需要支持这种格式，请使用 {@link NumberUtil#isInteger(String, boolean)} 方法。
     *
     * @param text 待测试的文本。
     * @return "true"表示文本内容是整数，"false"表示文本内容不是整数。
     */
    public static boolean isInteger(String text) {
        return isInteger(text, false);
    }

    /**
     * 判断文本内容是否为整数。
     *
     * @param text        待测试的文本。
     * @param supportSign 是否支持符号，设为"true"时可以识别带有正负号的数字；设为"false"时，传入带有正负号的文
     *                    本则被认为不是数字。
     * @return "true"表示文本内容是整数，"false"表示文本内容不是整数。
     */
    public static boolean isInteger(String text, boolean supportSign) {
        if (TextUtil.isEmpty(text)) {
            return false;
        }

        // 如果不支持符号，且字符串以"+"或"-"开头，则认为该字符串不是整数。
        if (!supportSign && (text.charAt(0) == '+' || text.charAt(0) == '-')) {
            return false;
        }

        // 如果能够转为Integer类型，则认为该字符串是整数。
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断文本内容是否为小数。
     * <p>
     * 整数也属于小数的一种。
     * <p>
     * 默认不支持正负号，若需要支持这种格式，请使用 {@link NumberUtil#isDecimal(String, boolean)} 方法。
     *
     * @param text 待测试的文本。
     * @return "true"表示文本内容是小数，"false"表示文本内容不是小数。
     */
    public static boolean isDecimal(String text) {
        return isDecimal(text, false);
    }

    /**
     * 判断文本内容是否为小数。
     * <p>
     * 整数也属于小数的一种。
     *
     * @param text        待测试的文本。
     * @param supportSign 是否支持符号，设为"true"时可以识别带有正负号的数字；设为"false"时，传入带有正负号的文
     *                    本则被认为不是数字。
     * @return "true"表示文本内容是小数，"false"表示文本内容不是小数。
     */
    public static boolean isDecimal(String text, boolean supportSign) {
        if (TextUtil.isEmpty(text)) {
            return false;
        }

        // 如果不支持符号，且字符串以"+"或"-"开头，则认为该字符串不是小数。
        if (!supportSign && (text.charAt(0) == '+' || text.charAt(0) == '-')) {
            return false;
        }

        // 如果能够转为Double类型，则认为该字符串是小数。
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * ----- 进制转换 -----
     */

    /**
     * 将数字转为十六进制文本。
     * <p>
     * 结果为单个字符时，自动在前面补"0"，且英文字母均为大写。
     *
     * @param data 待转换的数字。
     * @return 十六进制文本。永不为空，转换失败时将返回内容为空的字符串。
     */
    public static String toHexString(byte data) {
        return toHexString(data, true, true);
    }

    /**
     * 将数字转为十六进制文本。
     *
     * @param data        待转换的数字。
     * @param needPadding 结果为单个字符时，是否在前面补"0"。
     * @param isUpperCase 是否将结果转换为大写字母。
     * @return 十六进制文本。永不为空，转换失败时将返回内容为空的字符串。
     */
    public static String toHexString(byte data, boolean needPadding, boolean isUpperCase) {
        /*
         * 单个字节的取值范围是：[0, 255]，当它被读取为"byte"类型时，大于127的值将被映射到负数，因此我们需要将"byte"值和"0xFF"做与运
         * 算，丢弃符号位以获取原始数值。
         */
        String hex = Integer.toHexString(data & 0xFF);
        // 如果该字节对应的16进制文本只有一个字符，则在前面补"0"。
        if (needPadding && hex.length() < 2) {
            hex = "0" + hex;
        }

        if (isUpperCase) {
            return hex.toUpperCase();
        } else {
            return hex;
        }
    }

    /**
     * 将数组转为十六进制文本。
     * <p>
     * 元素为单个字符时，自动在前面补"0"。
     *
     * @param datas 待转换的数组。
     * @return 十六进制文本。永不为空，转换失败时将返回内容为空的字符串。
     */
    public static String toHexString(byte[] datas) {
        return toHexString(datas, true, true);
    }

    /**
     * 将数组转为十六进制文本。
     *
     * @param datas       待转换的数组。
     * @param needPadding 元素为单个字符时，是否在前面补"0"。
     * @param isUpperCase 是否将结果转换为大写字母。
     * @return 十六进制文本。永不为空，转换失败时将返回内容为空的字符串。
     */
    public static String toHexString(byte[] datas, boolean needPadding, boolean isUpperCase) {
        if (datas == null || datas.length == 0) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();
        for (byte b : datas) {
            String hex = toHexString(b, needPadding, isUpperCase);
            buffer.append(hex);
        }

        return buffer.toString();
    }

    /*
     * ----- 数字序号处理 -----
     */

    /**
     * 数字转为序号。
     * <p>
     * 在数字前添加"0"，生成序号。
     *
     * @param num    原始数值，必须大于0。
     * @param length 序号的位数。
     * @return 序号，参数不合法时将返回Null。
     */
    public static String toSerialNumber(int num, int length) {
        if (num <= 0 || length <= 0) {
            return null;
        }

        return String.format("%0" + length + "d", num);
    }
}
