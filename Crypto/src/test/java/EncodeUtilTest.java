import net.bi4vmr.javalib.crypto.EncodeUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Name        : EncodeUtilTest
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@qq.com
 * <p>
 * Date        : 2022-11-19 23:46
 * <p>
 * Description : EncodeUtil的测试类。
 */
public class EncodeUtilTest {

    @Test
    void test() {
        String s = "Hello World!";

        /* 消息摘要算法 */
        System.out.println("Test Text: " + s);
        System.out.println("MD5\t\t: " + EncodeUtil.getMD5(s));
        System.out.println("SHA-1\t: " + EncodeUtil.getSHA1(s));
        System.out.println("SHA-256\t: " + EncodeUtil.getSHA256(s));

        /* Base64算法 */
        String base64 = EncodeUtil.base64Encode(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 Encode\t: " + base64);
        System.out.println("Base64 Decode\t: " + new String(EncodeUtil.base64Decode(base64)));
    }
}
