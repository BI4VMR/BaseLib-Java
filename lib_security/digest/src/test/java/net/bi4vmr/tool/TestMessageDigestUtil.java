package net.bi4vmr.tool;

import net.bi4vmr.tool.java.security.digest.MessageDigestUtil;
import org.junit.jupiter.api.Test;

/**
 * MessageDigestUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestMessageDigestUtil {

    @Test
    void test() {
        String s = "Hello World!";

        /* 消息摘要算法 */
        System.out.println("Test Text: " + s);
        System.out.println("MD5\t\t: " + MessageDigestUtil.getMD5(s));
        System.out.println("SHA-1\t: " + MessageDigestUtil.getSHA1(s));
        System.out.println("SHA-256\t: " + MessageDigestUtil.getSHA256(s));
    }
}
