package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：压缩包 - TAR。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_TAR implements FileType {

    public static final String NAME = "TAR";
    public static final String INFO = "Tape Archive";

    private final String SUFFIX_TAR = "tar";

    private final String MIME_X_TAR = "application/x-tar";

    private final Set<String> suffixs = Set.of(SUFFIX_TAR);
    private final Set<String> mimes = Set.of(MIME_X_TAR);

    private Type_TAR() {
    }

    public static Type_TAR getInstance() {
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
        return SUFFIX_TAR;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_X_TAR;
    }

    @Override
    public boolean canMatch(File file) {
        // 第257-261字节固定为"ustar"(7573746172)
        String hex = FileIOUtil.readAsHexText(file, 257, 5);
        return "7573746172".equals(hex);
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_TAR INSTANCE = new Type_TAR();
    }
}
