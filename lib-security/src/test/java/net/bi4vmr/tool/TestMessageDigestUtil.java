package net.bi4vmr.tool;

import net.bi4vmr.tool.digest.MessageDigestUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Name        : TestMessageDigestUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-19 23:46
 * <p>
 * Description : MessageDigestUtil的测试类。
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
