package net.bi4vmr.tool;

import net.bi4vmr.tool.java.text.basecode.BaseCodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * BaseCodeUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestBaseCodeUtil {

    @Test
    void test_Base64() {
        String input = "Hello World!";

        /* Base64 */
        String base64 = BaseCodeUtil.encodeBase64(input.getBytes(StandardCharsets.UTF_8));
        System.out.println("Bytes Base64 Encode\t: " + base64);
        Assertions.assertEquals("SGVsbG8gV29ybGQh", base64);

        String rawText = new String(BaseCodeUtil.decodeBase64(base64));
        System.out.println("Base64 Decode\t: " + rawText);
        Assertions.assertEquals(input, rawText);
    }
}
