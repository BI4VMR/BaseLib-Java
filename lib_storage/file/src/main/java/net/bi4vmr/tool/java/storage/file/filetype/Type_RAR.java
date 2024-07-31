package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：压缩包 - RAR。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_RAR implements FileType {

    public static final String NAME = "RAR";
    public static final String INFO = "RAR Archive";

    private final String SUFFIX_RAR = "rar";

    private final String MIME_X_RAR = "application/x-rar-compressed";
    private final String MIME_VND_RAR = "application/vnd.rar";

    private final Set<String> suffixs = Set.of(SUFFIX_RAR);
    private final Set<String> mimes = Set.of(MIME_X_RAR, MIME_VND_RAR);

    private Type_RAR() {
    }

    public static Type_RAR getInstance() {
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
        return SUFFIX_RAR;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_X_RAR;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 7);
        }

        // 文件头部固定为"RARv4(526172211A0700)"或"RARv5(526172211A0701)"
        return head.startsWith("526172211A0700") || head.startsWith("526172211A0701");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_RAR INSTANCE = new Type_RAR();
    }
}
