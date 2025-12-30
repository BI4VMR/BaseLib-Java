package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.NumberUtil;
import net.bi4vmr.tool.java.io.base.FileIOUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 测试类：文件输入与输出工具。
 *
 * @author bi4vmr@outlook.com
 */
public class TestFileIOUtil {

    @Test
    void test() {
        // String s = FileIOUtil.readAsHexString(new File("C:\\Users\\bi4vmr\\Work\\#知识库插图"));
        // System.out.println(s);
        // File file = new File("C:\\Users\\bi4vmr\\Work\\#知识库插图");
        // File[] arr = file.listFiles();
        // for (File f : arr) {
        //     System.out.println("----- " + f.getName());
        //     FileIOUtil.readAsBytes(f,1,0,0);
        //     // System.out.println(s);
        // }

        // File file = new File("/home/bi4vmr/Download/1.tar");
        // File file = new File("/home/bi4vmr/Download/1.txt");
        File file = new File("C:/Users/bi4vmr/Downloads/FileZilla_3.69.5_win64-setup.exe");
        byte[] b= FileIOUtil.readAsBytes(file,2,8);
        System.out.println("Hex:[" + NumberUtil.toHexString(b) + "]");
        // readAsBytes(new File("D:\\Download\\压缩归档\\test.txt"),0,12,8192);
        // String datas = FileIOUtil.readAsHexText(file, 6, 0);
        // System.out.println("Hex:[" + datas + "]");
    }
}
