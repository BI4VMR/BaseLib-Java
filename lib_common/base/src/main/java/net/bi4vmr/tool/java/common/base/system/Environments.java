package net.bi4vmr.tool.java.common.base.system;

/**
 * JVM 系统属性名称。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class Environments {

    /**
     * 环境变量名称： {@code PATH} 。
     * <p>
     * 多平台通用变量，用于指明可执行文件的搜索路径。
     */
    public static final String PATH = "PATH";

    /**
     * 环境变量名称： {@code SHELL} 。
     * <p>
     * Unix 系平台变量，用于指明当前用户的默认 Shell 程序。
     */
    public static final String SHELL = "SHELL";

    /**
     * 环境变量名称： {@code TERM} 。
     * <p>
     * Unix 系平台变量，用于指明当前终端类型。
     */
    public static final String TERM = "TERM";

    /**
     * 环境变量名称： {@code APPDATA} 。
     * <p>
     * Windows 专有变量，值为当前用户的域内可漫游数据目录，通常是 {@code C:\Users\<用户名>\AppData\Roaming\ } 。
     */
    public static final String APPDATA_ROAMING = "APPDATA";

    /**
     * 环境变量名称： {@code LOCALAPPDATA} 。
     * <p>
     * Windows 专有变量，值为当前用户的域内不可漫游数据目录，通常是 {@code C:\Users\<用户名>\AppData\Local\ } 。
     */
    public static final String APPDATA_LOCAL = "LOCALAPPDATA";
}
