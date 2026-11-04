package net.bi4vmr.tool.java.common.base.file;

import net.bi4vmr.tool.java.common.base.system.CLIUtil;
import net.bi4vmr.tool.java.common.base.system.SystemUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * 文件相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class FileUtil {

    /**
     * 文件路径：无效值。
     * <p>
     * 用于表示未初始化的文件路径，可在不希望出现空值的场景使用。
     */
    public static final String PATH_INVALID = "/INVALID_PATH";

    /**
     * 文件路径：NULL 文件（类 Unix 系统）。
     * <p>
     * 用于消耗数据流的虚空文件，防止 {@link Process} 等发生阻塞。
     */
    public static final String PATH_NULL_UNIX = "/dev/null";

    /**
     * 文件路径：NULL 文件（Windows 系统）。
     * <p>
     * 用于消耗数据流的虚空文件，防止 {@link Process} 等发生阻塞。
     */
    public static final String PATH_NULL_WINDOWS = "NUL";

    /**
     * 文件：无效值。
     * <p>
     * 用于表示未初始化的文件路径，可在不希望出现空值的场景使用。
     */
    public static final File FILE_INVALID = new File(PATH_INVALID);


    private FileUtil() {
        // 静态工具类，无需实例化。
    }


    /**
     * 获取最后修改时间戳。
     *
     * @param file 目标文件。
     * @return 时间戳。
     */
    public static long getModifyTimestamp(File file) {
        try {
            return Files.getLastModifiedTime(file.toPath()).toMillis();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取最后修改时间戳。
     *
     * @param path 目标文件路径。
     * @return 时间戳。
     */
    public static long getModifyTimestamp(String path) {
        return getModifyTimestamp(new File(path));
    }

    /**
     * 更新最后修改时间戳。
     *
     * @param file      目标文件。
     * @param timestamp 时间戳。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setModifyTime(File file, long timestamp) {
        try {
            Files.setLastModifiedTime(file.toPath(), FileTime.fromMillis(timestamp));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新最后修改时间戳。
     *
     * @param path      目标文件路径。
     * @param timestamp 时间戳。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setModifyTime(String path, long timestamp) {
        return setModifyTime(new File(path), timestamp);
    }

    /**
     * 获取最后修改时间。
     *
     * @param file 目标文件。
     * @return 当前时区的 {@code ZonedDateTime} 实例。
     */
    public static ZonedDateTime getModifyTime(File file) {
        try {
            Instant instant = Files.getLastModifiedTime(file.toPath()).toInstant();
            return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 更新最后修改时间戳。
     *
     * @param path 目标文件路径。
     * @return 当前时区的 {@code ZonedDateTime} 实例。
     */
    public static ZonedDateTime getModifyTime(String path) {
        return getModifyTime(new File(path));
    }

    /**
     * 更新最后修改时间。
     *
     * @param file 目标文件。
     * @param time 目标时间。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setModifyTime(File file, ZonedDateTime time) {
        try {
            return setModifyTime(file, time.toInstant().toEpochMilli());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新最后修改时间。
     *
     * @param path 目标文件路径。
     * @param time 目标时间。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setModifyTime(String path, ZonedDateTime time) {
        return setModifyTime(new File(path), time.toInstant().toEpochMilli());
    }

    /**
     * 获取文件创建时间戳。
     *
     * @param file 目标文件。
     * @return 时间戳。
     */
    public static long getCreateTimestamp(File file) {
        try {
            return Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toMillis();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取文件创建时间戳。
     *
     * @param path 目标文件路径。
     * @return 时间戳。
     */
    public static long getCreateTimestamp(String path) {
        return getCreateTimestamp(new File(path));
    }

    /**
     * 获取文件创建时间。
     *
     * @param file 目标文件。
     * @return 当前时区的 {@code ZonedDateTime} 实例。
     */
    public static ZonedDateTime getCreateTime(File file) {
        try {
            Instant instant = Files.readAttributes(file.toPath(), BasicFileAttributes.class)
                    .creationTime()
                    .toInstant();
            return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取文件创建时间。
     *
     * @param path 目标文件路径。
     * @return 当前时区的 {@code ZonedDateTime} 实例。
     */
    public static ZonedDateTime getCreateTime(String path) {
        return getCreateTime(new File(path));
    }

    /**
     * 更新文件创建时间。
     * <p>
     * 该方法只能在拥有 PowerShell 环境的 Windows 系统中使用，类 Unix 系统不提供此类功能。
     *
     * @param file 目标文件。
     * @param time 目标时间。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setCreateTime(File file, ZonedDateTime time) {
        String timeText = time.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        List<String> command = Arrays.asList(
                "powershell", "-Command",
                "\"Set-ItemProperty",
                "-Path '" + file.getAbsolutePath() + "'",
                "-Name CreationTime",
                "-Value '" + timeText + "'\"");
        int status = CLIUtil.runForStatus(command.toArray(new String[0]));
        return CLIUtil.isSuccess(status);
    }

    /**
     * 更新文件创建时间。
     * <p>
     * 该方法只能在拥有 PowerShell 环境的 Windows 系统中使用，类 Unix 系统不提供此类功能。
     *
     * @param path 目标文件路径。
     * @param time 目标时间。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setCreateTime(String path, ZonedDateTime time) {
        return setCreateTime(new File(path), time);
    }

    /**
     * 更新文件创建时间。
     * <p>
     * 该方法只能在拥有 PowerShell 环境的 Windows 系统中使用，类 Unix 系统不提供此类功能。
     *
     * @param file      目标文件。
     * @param timestamp 目标时间（时间戳）。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setCreateTime(File file, long timestamp) {
        ZonedDateTime utcTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.UTC);
        return setCreateTime(file, utcTime);
    }

    /**
     * 更新文件创建时间。
     * <p>
     * 该方法只能在拥有 PowerShell 环境的 Windows 系统中使用，类 Unix 系统不提供此类功能。
     *
     * @param path      目标文件路径。
     * @param timestamp 目标时间（时间戳）。
     * @return 修改成功时返回 {@code true} ；修改失败时返回 {@code false} 。
     */
    public static boolean setCreateTime(String path, long timestamp) {
        return setCreateTime(new File(path), timestamp);
    }

    /**
     * 获取虚空文件节点。
     * <p>
     * 用于消耗数据流的虚空文件，防止 {@link Process} 等发生阻塞。
     * <p>
     * 使用示例：
     *
     * <pre>
     * {@code Process process = new ProcessBuilder(command)
     *                .redirectOutput(getNullFile())
     *                .start();}
     * </pre>
     * <p>
     * 在 Java 9 或更高版本中， ProcessBuilder 已有内置的虚空文件节点，无需引入本工具。
     *
     * <pre>
     * {@code Process process = new ProcessBuilder(command)
     *                .redirectOutput(ProcessBuilder.Redirect.DISCARD)
     *                .start();}
     * </pre>
     */
    public static File getNullFile() {
        if (SystemUtil.isWindows()) {
            return new File(PATH_NULL_WINDOWS);
        } else {
            // 非 Windows 系统都当作类 Unix 系统处理
            return new File(PATH_NULL_UNIX);
        }
    }
}
