package net.bi4vmr.tool;

import net.bi4vmr.tool.java.security.digest.MessageDigestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * MessageDigestUtil的测试类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestMessageDigestUtil {

    @Test
    void test_InputText() {
        /* 输入项为字符串 */
        String text = "Hello World!";

        String md5 = MessageDigestUtil.getMD5(text);
        System.out.println("Text MD5: \n" + md5);
        String sha1 = MessageDigestUtil.getSHA1(text);
        System.out.println("Text SHA-1: \n" + sha1);
        String sha256 = MessageDigestUtil.getSHA256(text);
        System.out.println("Text SHA-256: \n" + sha256);

        // 计算结果应该和预设值相等
        Assertions.assertEquals("ED076287532E86365E841E92BFC50D8C", md5);
        Assertions.assertEquals("2EF7BDE608CE5404E97D5F042F95F89F1C232871", sha1);
        Assertions.assertEquals("7F83B1657FF1FC53B92DC18148A1D65DFC2D4B1FA3D677284ADDD200126D9069", sha256);
    }

    @Test
    void test_InputBytes() {
        /* 输入项为字符串 */
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);

        String md5 = MessageDigestUtil.getMD5(data);
        System.out.println("Bytes MD5: \n" + md5);
        String sha1 = MessageDigestUtil.getSHA1(data);
        System.out.println("Bytes SHA-1: \n" + sha1);
        String sha256 = MessageDigestUtil.getSHA256(data);
        System.out.println("Bytes SHA-256: \n" + sha256);

        // 计算结果应该和预设值相等
        Assertions.assertEquals("ED076287532E86365E841E92BFC50D8C", md5);
        Assertions.assertEquals("2EF7BDE608CE5404E97D5F042F95F89F1C232871", sha1);
        Assertions.assertEquals("7F83B1657FF1FC53B92DC18148A1D65DFC2D4B1FA3D677284ADDD200126D9069", sha256);
    }

    @Test
    void test_InputFile() {
        /* 输入项为文件 */
        File file = new File("./build.gradle.kts");

        String md5 = MessageDigestUtil.getMD5(file);
        System.out.println("File MD5: \n" + md5);
        String sha1 = MessageDigestUtil.getSHA1(file);
        System.out.println("File SHA-1: \n" + sha1);
        String sha256 = MessageDigestUtil.getSHA256(file);
        System.out.println("File SHA-256: \n" + sha256);

        // 计算结果应该和预设值相等
        Assertions.assertEquals("0BEA39C450A79F386690FA8214B54328", md5);
        Assertions.assertEquals("242AE29806780890D1C5C22BD0C8F1A274878118", sha1);
        Assertions.assertEquals("7C39C9C9777749CB6CC890084453354399F063FD9B9D92C0DFB70C2C133DDDFC", sha256);
    }
}
