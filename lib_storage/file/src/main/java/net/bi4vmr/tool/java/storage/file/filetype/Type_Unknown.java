package net.bi4vmr.tool.java.storage.file.filetype;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：占位符，表示所有未知的类型。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_Unknown implements FileType {

    public static final String NAME = "Unknown";
    public static final String INFO = "Unknown";

    private Type_Unknown() {
    }

    public static Type_Unknown getInstance() {
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
        return Set.of();
    }

    @Override
    public String getSuffixName() {
        return "";
    }

    @Override
    public Set<String> getMIMETypes() {
        return Set.of();
    }

    @Override
    public String getMIMEType() {
        return "";
    }

    @Override
    public boolean canMatch(File file) {
        // 默认类型，匹配所有文件，因此需要放在匹配列表的末尾。
        return true;
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_Unknown INSTANCE = new Type_Unknown();
    }
}
