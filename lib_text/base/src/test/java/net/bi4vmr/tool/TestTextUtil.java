package net.bi4vmr.tool;

import net.bi4vmr.tool.java.text.base.TextUtil;
import org.junit.jupiter.api.Test;

/**
 * TextUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestTextUtil {

    @Test
    void test() {
        String s1 = null;
        String s2 = "";
        String s3 = "Hello World!";

        System.out.println("s1: " + s1 + " is empty? " + TextUtil.isEmpty(s1));
        System.out.println("s2: " + s2 + " is empty? " + TextUtil.isEmpty(s2));
        System.out.println("s3: " + s3 + " is empty? " + TextUtil.isEmpty(s3));
        System.out.println();

        System.out.println("s1: " + s1 + " is not empty? " + TextUtil.isNotEmpty(s1));
        System.out.println("s2: " + s2 + " is not empty? " + TextUtil.isNotEmpty(s2));
        System.out.println("s3: " + s3 + " is not empty? " + TextUtil.isNotEmpty(s3));
        System.out.println();

        String s4 = "012";
        System.out.println(TextUtil.getSerialNumber(s4, 3));
        System.out.println(TextUtil.getSerialNumber(s4, 4));
        System.out.println(TextUtil.getSerialNumber(s4, 5));
    }
}
