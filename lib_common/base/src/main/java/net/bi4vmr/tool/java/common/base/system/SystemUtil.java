package net.bi4vmr.tool.java.common.base.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 设备与系统工具。
 * <p>
 * 获取硬件平台、操作系统、 JVM 等环境信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class SystemUtil {

    /**
     * 日志工具。
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    /**
     * 当前操作系统类型。
     * <p>
     * 进程启动后操作系统类型不会改变，因此仅需在类加载时获取一次该值即可。
     */
    private static final OSType OS_TYPE = getOSTypeInner();

    /**
     * 当前平台架构名称。
     * <p>
     * 进程启动后操作系统类型不会改变，因此仅需在类加载时获取一次该值即可。
     */
    private static final ArchType OS_ARCH = getArchTypeInner();


    private SystemUtil() {
        // 静态工具类，无需实例化。
    }


    /*
     * ----- 系统信息 -----
     */

    /**
     * 获取当前操作系统类型。
     * <p>
     * 类加载时通过 {@link #getOSTypeInner()} 方法解析当前系统类型，后续返回缓存变量的值。
     *
     * @return {@link OSType} 枚举常量。
     */
    public static OSType getOSType() {
        return OS_TYPE;
    }

    /**
     * 获取当前平台架构类型。
     * <p>
     * 类加载时通过 {@link #getArchTypeInner()} 方法解析当前平台架构类型，后续返回缓存变量的值。
     *
     * @return {@link ArchType} 枚举常量。
     */
    public static ArchType getArchType() {
        return OS_ARCH;
    }

    /**
     * 判断当前操作系统是否为 Windows 。
     *
     * @return {@code true} 表示操作系统为 Windows ； {@code false} 表示其他系统。
     */
    public static boolean isWindows() {
        return getOSType() == OSType.WINDOWS;
    }

    /**
     * 判断当前操作系统是否为 Linux 。
     *
     * @return {@code true} 表示操作系统为 Linux ； {@code false} 表示其他系统。
     */
    public static boolean isLinux() {
        return getOSType() == OSType.LINUX;
    }

    /**
     * 判断当前操作系统是否为 MacOS 。
     *
     * @return {@code true} 表示操作系统为 MacOS ； {@code false} 表示其他系统。
     */
    public static boolean isMacOS() {
        return getOSType() == OSType.MACOS;
    }

    /**
     * 获取当前操作系统类型。
     * <p>
     * 将 {@code os.name} 系统属性与已知系统名称列表进行匹配，判断当前运行环境。
     *
     * @return {@link OSType} 枚举常量。
     */
    private static OSType getOSTypeInner() {
        String osName = System.getProperty(Propertys.OS_NAME);
        for (OSType type : OSType.values()) {
            if (type.isAliasMatch(osName)) {
                return type;
            }
        }

        return OSType.UNKNOWN;
    }

    /**
     * 获取当前平台架构名称。
     * <p>
     * 将 {@code os.arch} 系统属性与已知平台架构列表进行匹配，判断当前运行环境。
     *
     * @return {@link ArchType} 枚举常量。
     */
    private static ArchType getArchTypeInner() {
        String osArch = System.getProperty(Propertys.OS_ARCH);
        for (ArchType type : ArchType.values()) {
            if (type.isAliasMatch(osArch)) {
                return type;
            }
        }

        return ArchType.UNKNOWN;
    }

    /**
     * 获取用户名称。
     *
     * @return 用户名称。
     */
    private static String getUserName() {
        return System.getProperty(Propertys.USER_NAME);
    }

    /**
     * 获取用户目录。
     *
     * @return 用户数据目录。
     */
    private static File getUserHome() {
        String path = System.getProperty(Propertys.USER_HOME);
        return new File(path);
    }

    /**
     * 获取工作目录。
     * <p>
     * 用户启动 Java 进程时所在的目录，不一定是当前程序文件所在的目录，通常在 CLI 场景中有作用。
     * <p>
     * 在 IDE 调试环境中通常为工程根目录。
     *
     * @return 工作目录。
     */
    private static File getWorkDirectory() {
        String path = System.getProperty(Propertys.WORK_DIR);
        return new File(path);
    }


    /*
     * ----- 环境变量 -----
     */

    /**
     * 获取环境变量 {@code PATH} 的值。
     *
     * @return 变量值。非空，罕见的情况下将返回空字符串。
     */
    public static String getPath() {
        String rawValue = System.getenv(Environments.PATH);
        if (rawValue == null) {
            logger.error("Environment variable [PATH] is not exist!");
            return "";
        }

        return rawValue;
    }

    /**
     * 获取环境变量 {@code SHELL} 的值。
     *
     * @return 变量值。非空，罕见的情况下将返回空字符串。
     */
    public static String getShell() {
        String rawValue = System.getenv(Environments.SHELL);
        if (rawValue == null) {
            logger.error("Environment variable [SHELL] is not exist!");
            return "";
        }

        return rawValue;
    }

    /**
     * 获取环境变量 {@code PATH} 的目录数组。
     * <p>
     * 根据平台解析变量值，每个数组元素表示一个目录。
     *
     * @return 目录数组。
     */
    public static String[] getPathDirectories() {
        return getPath().split(File.pathSeparator);
    }

    /**
     * 获取当前用户的可漫游数据目录。
     * <p>
     * Windows 专有变量，值为当前用户的域内可漫游数据目录，通常是 {@code C:\Users\<用户名>\AppData\Roaming\ } 。
     *
     * @return 数据目录。
     */
    public static File getUserDataRoamingDirectory() {
        String rawValue = System.getenv(Environments.APPDATA_ROAMING);
        // 正常情况下该变量不会为空，此处对特殊场景作容错处理，手动拼接路径。
        if (rawValue == null) {
            return new File(getUserHome(), "AppData" + File.separator + "Roaming");
        }

        return new File(rawValue);
    }

    /**
     * 获取当前用户的不可漫游数据目录。
     * <p>
     * Windows 专有变量，值为当前用户的域内不可漫游数据目录，通常是 {@code C:\Users\<用户名>\AppData\Local\ } 。
     *
     * @return 数据目录。
     */
    public static File getUserDataLocalDirectory() {
        String rawValue = System.getenv(Environments.APPDATA_LOCAL);
        // 正常情况下该变量不会为空，此处对特殊场景作容错处理，手动拼接路径。
        if (rawValue == null) {
            return new File(getUserHome(), "AppData" + File.separator + "Local");
        }

        return new File(rawValue);
    }
}
