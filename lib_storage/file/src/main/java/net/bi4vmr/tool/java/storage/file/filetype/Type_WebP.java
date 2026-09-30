package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.io.base.FileIOUtil;
import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 文件类型：WebP图像。
 * <p>
 * 该文件的格式可参考以下链接：
 * <p>
 * <a href="https://developers.google.com/speed/webp/docs/riff_container">Google WebP标准</a>
 *
 * @author BI4VMR@outlook.com
 */
public class Type_WebP implements FileType {

    public static final String NAME = "WebP";
    public static final String INFO = "WebP";

    private final String SUFFIX_WEBP = "webp";

    private final String MIME_WEBP = "image/webp";

    private final Set<String> suffixs = Set.of(SUFFIX_WEBP);
    private final Set<String> mimes = Set.of(MIME_WEBP);

    private Type_WebP() {
    }

    public static Type_WebP getInstance() {
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
        return SUFFIX_WEBP;
    }

    @Override
    public Set<String> getMIMETypes() {
        return mimes;
    }

    @Override
    public String getMIMEType() {
        return MIME_WEBP;
    }

    @Override
    public boolean canMatch(File file) {
        // 首先从缓存中读取文件头部信息
        String head = FileTypeUtil.getFileHeadCache();
        // 如果当前缓存为空，说明是用户直接调用本方法进行匹配，需要自行读取文件头部信息。
        if (head == null) {
            head = FileIOUtil.readAsHexText(file, 12);
        }

        // 文件头部固定为"RIFF(52494646)"，且第8-12字节固定为"WEBP(57454250)"。
        return head.startsWith("52494646") && head.startsWith("57454250", 16);
    }

    @Override
    public String toString() {
        return NAME;
    }

    // 静态内部类，用于承载单例对象。
    private static class SingletonHolder {
        private static final Type_WebP INSTANCE = new Type_WebP();
    }
}
