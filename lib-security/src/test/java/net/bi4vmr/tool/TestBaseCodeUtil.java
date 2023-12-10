package net.bi4vmr.tool;

import net.bi4vmr.tool.basecode.BaseCodeUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Name        : TestBaseCodeUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-19 23:46
 * <p>
 * Description : BaseCodeUtil的测试类。
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
