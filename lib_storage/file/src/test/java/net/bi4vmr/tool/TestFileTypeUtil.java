package net.bi4vmr.tool;

import net.bi4vmr.tool.java.storage.file.FileTypeUtil;
import net.bi4vmr.tool.java.storage.file.filetype.FileType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 测试类：文件类型工具。
 *
 * @author BI4VMR@outlook.com
 */
public class TestFileTypeUtil {

    @Test
    void test() throws IOException {
        // FileTypeUtil.registerFileType(Type_EXE.getInstance());
        // FileTypeUtil.getCustomFileTypes().forEach(fileType -> {
        //     System.out.println("t: "+fileType);
        // });
        // FileTypeUtil.unregisterFileType(Type_EXE.getInstance());

        // File testDir = new File("D:\\Download\\1");
        // File testDir = new File("/home/bi4vmr/Download/");
        File testDir = new File("/home/bi4vmr/Project/BaseLib/BaseLib-Java");
        File[] array = testDir.listFiles();
        if (array == null) {
            System.out.println("目录不存在！");
            return;
        }

        for (File file : array) {
            if (file.isDirectory()) {
                continue;
            }

            System.out.println("----- 文件名称：[" + file.getName() + "] -----");
            System.out.println("Files probe MIME:[" + Files.probeContentType(file.toPath()) + "]");
            FileType fileType = FileTypeUtil.probeTypeByContent(file);
            System.out.println("Util probe Type:[" + fileType.getDescription() + "]");
            System.out.println();
        }
    }
}
