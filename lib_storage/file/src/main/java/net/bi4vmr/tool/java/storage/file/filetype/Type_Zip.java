package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：压缩包 - ZIP。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_Zip implements FileType {

    public static final String NAME = "Zip";
    public static final String INFO = "Zip Archive";

    private final String SUFFIX_ZIP = "zip";

    private final String MIME_ZIP_X = "application/x-zip-compressed";

    private final Set<String> suffixs = Set.of(SUFFIX_ZIP);
    private final Set<String> mimes = Set.of(MIME_ZIP_X);

    private Type_Zip() {
    }

    public static Type_Zip getInstance() {
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
        return SUFFIX_ZIP;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_ZIP_X;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 2);
        }

        // 文件头部固定为"504B"
        return head.startsWith("504B");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_Zip INSTANCE = new Type_Zip();
    }
}
