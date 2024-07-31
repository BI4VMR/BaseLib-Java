package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：压缩包 - BZip2。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_BZip2 implements FileType {

    public static final String NAME = "BZip2";
    public static final String INFO = "BZip2 Archive";

    private final String SUFFIX_BZ2 = "bz2";

    private final String MIME_BZ2 = "application/bz2";
    private final String MIME_X_BZIP2 = "application/x-bzip2";

    private final Set<String> suffixs = Set.of(SUFFIX_BZ2);
    private final Set<String> mimes = Set.of(MIME_BZ2, MIME_X_BZIP2);

    private Type_BZip2() {
    }

    public static Type_BZip2 getInstance() {
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
        return SUFFIX_BZ2;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_X_BZIP2;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 3);
        }

        // 文件头部固定为"425A68"
        return head.startsWith("425A68");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_BZip2 INSTANCE = new Type_BZip2();
    }
}
