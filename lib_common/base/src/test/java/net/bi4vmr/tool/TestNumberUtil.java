package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.NumberUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 数字处理工具的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestNumberUtil {

    @Test
    void test() {

        System.out.println("----- 数字与文本转换 -----");

        String s1 = "12";
        String s2 = "-12";
        String s3 = "012";
        String s4 = "32.5";
        String s5 = "-32.5";

        System.out.println(s1 + " is integer? " + NumberUtil.isInteger(s1));
        System.out.println(s2 + " is integer? " + NumberUtil.isInteger(s2));
        System.out.println(s3 + " is integer? " + NumberUtil.isInteger(s3));
        System.out.println(s4 + " is integer? " + NumberUtil.isInteger(s4));
        System.out.println(s5 + " is integer? " + NumberUtil.isInteger(s5));

        System.out.println(s1 + " is decimal? " + NumberUtil.isDecimal(s1));
        System.out.println(s2 + " is decimal? " + NumberUtil.isDecimal(s2));
        System.out.println(s3 + " is decimal? " + NumberUtil.isDecimal(s3));
        System.out.println(s4 + " is decimal? " + NumberUtil.isDecimal(s4));
        System.out.println(s5 + " is decimal? " + NumberUtil.isDecimal(s5));

        System.out.println();
        System.out.println("----- 数字转十六进制文本 -----");

        byte num1 = 10;
        byte num2 = 127;
        byte num3 = -1;

        System.out.println(num1 + " to Hex: " + NumberUtil.toHexString(num1));
        System.out.println(num2 + " to Hex: " + NumberUtil.toHexString(num2));
        System.out.println(num3 + " to Hex: " + NumberUtil.toHexString(num3));

        System.out.println();
        System.out.println("----- 字节数组转十六进制文本 -----");

        byte[] array1 = new byte[]{10, -10, 127};
        byte[] array2 = new byte[]{10, 16, 32, 127};

        System.out.println(Arrays.toString(array1) + " to Hex: " + NumberUtil.toHexString(array1));
        System.out.println(Arrays.toString(array2) + " to Hex: " + NumberUtil.toHexString(array2));

        System.out.println();
        System.out.println("----- 数字转为序号 -----");

        int num4 = 8;
        int num5 = 32;
        System.out.println(num4 + " to serial number: " + NumberUtil.toSerialNumber(num4, 2));
        System.out.println(num4 + " to serial number: " + NumberUtil.toSerialNumber(num4, 4));
        System.out.println(num5 + " to serial number: " + NumberUtil.toSerialNumber(num5, 2));
        System.out.println(num5 + " to serial number: " + NumberUtil.toSerialNumber(num5, 4));
    }
}
