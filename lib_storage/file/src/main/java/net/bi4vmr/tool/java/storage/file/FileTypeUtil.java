package net.bi4vmr.tool.java.storage.file;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.filetype.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 文件类型工具。
 *
 * @author BI4VMR@outlook.com
 */
public class FileTypeUtil {

    /**
     * 文件类型对象列表。
     * <p>
     * 记录所有已知的文件类型；当我们推测文件类型时，从前往后依次匹配每个表项。
     */
    private static final List<FileType> fileTypes = new CopyOnWriteArrayList<>();

    /**
     * 文件头部内容缓存。
     * <p>
     * 当我们推测文件类型时，需要将文件头部内容与各个已知类型相匹配，每次匹配都读取文件内容效率较低，因此遍历列表前先读取一次文件头部内容
     * 并存入该变量，后续匹配过程中直接读出即可。
     */
    private static String fileHeadCache;

    /*
     * 注册已知文件类型。
     *
     * 进行类型匹配操作时将按照注册顺序遍历列表，一旦匹配成功则不再继续匹配后续表项，因此判断条件更具体的项需要注册在列表顶部。
     */
    static {
        // ----- 图像 -----
        fileTypes.add(Type_JPEG.getInstance());
        fileTypes.add(Type_PNG.getInstance());
        fileTypes.add(Type_BMP.getInstance());
        fileTypes.add(Type_GIF.getInstance());
        fileTypes.add(Type_WebP.getInstance());
        fileTypes.add(Type_TIFF.getInstance());

        // ----- 压缩归档 -----
        fileTypes.add(Type_Zip.getInstance());
        fileTypes.add(Type_7Zip.getInstance());
        fileTypes.add(Type_GZip.getInstance());
        fileTypes.add(Type_BZip2.getInstance());
        fileTypes.add(Type_XZ.getInstance());
        fileTypes.add(Type_RAR.getInstance());
        fileTypes.add(Type_TAR.getInstance());

        // ----- 程序 -----
        fileTypes.add(Type_EXE.getInstance());
    }

    /**
     * 推断文件类型（根据内容）。
     *
     * @param file 待测试的文件对象。
     * @return 文件类型。
     */
    public static synchronized FileType probeTypeByContent(File file) {
        if (file == null || !file.exists() || file.isDirectory() || !file.canRead()) {
            return Type_Unknown.getInstance();
        }

        // 读取文件头部的若干字节
        String head = FileIOUtil.readAsHexText(file, 32);
        // TODO TestOnly
        System.out.println("Head32:[" + head + "]");
        // 缓存至全局变量
        fileHeadCache = head;
        // 遍历已知类型列表查找匹配的项
        for (FileType type : fileTypes) {
            if (type.canMatch(file)) {
                // 清空缓存
                fileHeadCache = null;
                return type;
            }
        }

        // 如果已知类型均未匹配，则匹配占位符类型。
        fileHeadCache = null;
        return Type_Unknown.getInstance();
    }

    /**
     * 获取缓存的文件头部内容。
     * <p>
     * 该方法仅在用户调用{@link #probeTypeByContent(File)}方法匹配文件类型期间有效，匹配结束后将返回空值。
     * <p>
     * 对于自定义文件类型，可以在{@link FileType#canMatch(File)}方法中调用该方法获取文件头部内容；对于其他场合则没有必要调用该方法。
     *
     * @return 文件头部内容（16进制）。
     */
    public static String getFileHeadCache() {
        return fileHeadCache;
    }

    /**
     * 获取已注册的自定义类型。
     *
     * @return 列表，元素类型为{@link FileType}。
     */
    public static List<FileType> getCustomFileTypes() {
        return fileTypes.stream()
                .filter(type -> !type.isBuiltinType())
                .collect(Collectors.toList());
    }

    /**
     * 注册自定义文件类型。
     * <p>
     * 较晚注册的类型将会出现在列表头部，因此工具使用者应当先注册匹配条件更为宽泛的文件类型。
     *
     * @param fileType 文件类型对象。
     */
    public synchronized static void registerFileType(FileType fileType) {
        if (!fileType.isBuiltinType() && !fileTypes.contains(fileType)) {
            // 将自定义类型插入到集合顶部，以便优先匹配。
            fileTypes.add(0, fileType);
        }
    }

    /**
     * 注销自定义文件类型。
     *
     * @param fileType 文件类型对象。
     */
    public synchronized static void unregisterFileType(FileType fileType) {
        if (!fileType.isBuiltinType()) {
            fileTypes.remove(fileType);
        }
    }
}
