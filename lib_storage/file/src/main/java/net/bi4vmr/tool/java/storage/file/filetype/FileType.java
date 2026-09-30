package net.bi4vmr.tool.java.storage.file.filetype;

import net.bi4vmr.tool.java.storage.file.FileTypeUtil;

import java.io.File;
import java.util.Set;

/**
 * 接口：文件类型。
 *
 * @author BI4VMR@outlook.com
 */
public interface FileType {

    /**
     * 获取该类型的标准名称。
     *
     * @return 标准名称。
     */
    String getStandardName();

    /**
     * 获取该类型的详细信息。
     * <p>
     * 如果没有介绍内容，则返回标准名称。
     *
     * @return 详细信息。
     */
    String getDescription();

    /**
     * 判断该类型是否为工具内置类型。
     * <p>
     * 工具内置类型不能从集合中移除；而工具使用者自定义的类型可以自由地被添加至集合，也可以随时从集合中移除。
     *
     * @return "true"表示工具内置类型，"false"表示工具使用者自定义的类型。
     */
    boolean isBuiltinType();

    /**
     * 获取该类型的文件后缀名称列表。
     *
     * @return 文件后缀名称列表。
     */
    Set<String> getSuffixNames();

    /**
     * 获取该类型的常用文件后缀名称。
     *
     * @return 文件后缀名称。
     */
    String getSuffixName();

    /**
     * 获取该类型的MIME列表。
     *
     * @return MIME列表。
     */
    Set<String> getMIMETypes();

    /**
     * 获取该类型的常用MIME。
     *
     * @return 文件后缀名称。
     */
    String getMIMEType();

    /**
     * 比较文件是否为当前类型。
     * <p>
     * 当我们调用 {@link FileTypeUtil#probeTypeByContent(File)} 方法判断文件类型时，该方法将被回调。
     * <p>
     * 虽然该方法持有文件句柄，可以自行读取文件内容，但是匹配文件类型需要回调所有{@link FileType}对象的本方法，统一读取一次文件头部内容
     * 并缓存至变量，匹配时再通过{@link FileTypeUtil#getFileHeadCache()}方法读取效率更高。
     *
     * @param file 待匹配的文件对象。
     * @return "true"表示传入的文件与当前类型匹配，"false"表示类型不匹配。
     */
    boolean canMatch(File file);
}
