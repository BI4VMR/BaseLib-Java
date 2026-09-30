package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：图像 - Windows BMP。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_BMP implements FileType {

    public static final String NAME = "BMP";
    public static final String INFO = "Windows BMP";

    private final String SUFFIX_BMP = "bmp";

    private final String MIME_BMP = "image/bmp";
    private final String MIME_BMP_X = "image/x-ms-bmp";

    private final Set<String> suffixs = Set.of(SUFFIX_BMP);
    private final Set<String> mimes = Set.of(MIME_BMP, MIME_BMP_X);

    private Type_BMP() {
    }

    public static Type_BMP getInstance() {
        // 返回静态内部类中的单例对象
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String getStandardName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return INFO;
    }

    @Override
    public boolean isBuiltinType() {
        return true;
    }

    @Override
    public Set<String> getSuffixNames() {
        return suffixs;
    }

    @Override
    public String getSuffixName() {
        return SUFFIX_BMP;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_BMP;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 2);
        }

        // 文件头部固定为"424D"
        return head.startsWith("424D");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_BMP INSTANCE = new Type_BMP();
    }
}
