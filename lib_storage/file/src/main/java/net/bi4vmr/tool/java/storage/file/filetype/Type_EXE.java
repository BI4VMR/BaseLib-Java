package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：程序 - Windows可执行文件。
 *
 * @author BI4VMR@outlook.com
 */
public class Type_EXE implements FileType {

    public static final String NAME = "EXE";
    public static final String INFO = "Executable for Windows";

    private final String SUFFIX_EXE = "exe";

    private final String MIME_EXE_X1 = "application/x-msdos-program";
    private final String MIME_EXE_X2 = "application/x-msdownload";
    private final String MIME_EXE_VND = "application/vnd.microsoft.portable-executable";

    private final Set<String> suffixs = Set.of(SUFFIX_EXE);
    private final Set<String> mimes = Set.of(MIME_EXE_X1, MIME_EXE_X2, MIME_EXE_VND);

    private Type_EXE() {
    }

    public static Type_EXE getInstance() {
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
        return SUFFIX_EXE;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_EXE_X1;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 2);
        }

        // 文件头部固定为"4D5A"
        return head.startsWith("4D5A");
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_EXE INSTANCE = new Type_EXE();
    }
}
