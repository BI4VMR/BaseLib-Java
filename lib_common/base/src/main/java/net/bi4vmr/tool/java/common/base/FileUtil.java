package net.bi4vmr.tool.java.common.base;

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
}
