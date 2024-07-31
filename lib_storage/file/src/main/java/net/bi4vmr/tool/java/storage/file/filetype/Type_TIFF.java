package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：TIFF图像。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_TIFF implements FileType {

    public static final String NAME = "TIFF";
    public static final String INFO = "Tag Image File Format";

    private final String SUFFIX_TIFF = "tiff";
    private final String SUFFIX_TIF = "tif";

    private final String MIME_TIFF = "image/tiff";

    private final Set<String> suffixs = Set.of(SUFFIX_TIFF, SUFFIX_TIF);
    private final Set<String> mimes = Set.of(MIME_TIFF);

    private Type_TIFF() {
    }

    public static Type_TIFF getInstance() {
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
        return SUFFIX_TIFF;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_TIFF;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 4);
        }

        // 文件头部固定为"49492A00"
        return head.startsWith("49492A00");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_TIFF INSTANCE = new Type_TIFF();
    }
}
