package net.bi4vmr.tool.java.common.base.system;

/**
 * JVM 系统属性名称。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class Propertys {

    /**
     * 系统属性：操作系统名称。
     */
    public static final String OS_NAME = "os.name";

    /**
     * 系统属性：平台架构名称。
     */
    public static final String OS_ARCH = "os.arch";

    /**
     * 系统属性：操作系统版本。
     */
    public static final String OS_VERSION = "os.version";

    /**
     * 系统属性：用户名称。
     */
    public static final String USER_NAME = "user.name";

    /**
     * 系统属性：用户主目录。
     */
    public static final String USER_HOME = "user.home";

    /**
     * 系统属性：工作目录。
     * <p>
     * 用户启动 Java 进程时所在的目录，不一定是当前程序文件所在的目录，通常在 CLI 场景中有作用。
     * <p>
     * 在 IDE 调试环境中通常为工程根目录。
     */
    public static final String WORK_DIR = "user.dir";
}
