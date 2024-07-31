package net.bi4vmr.tool;

import net.bi4vmr.tool.java.text.base.basecode.BaseCodeUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * BaseCodeUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestBaseCodeUtil {

    @Test
    void test() {
        String s = "Hello World!";

        /* Base64算法 */
        String base64 = BaseCodeUtil.encodeBase64(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 Encode\t: " + base64);
        String rawText = new String(BaseCodeUtil.decodeBase64(base64));
        System.out.println("Base64 Decode\t: " + rawText);
    }
}
