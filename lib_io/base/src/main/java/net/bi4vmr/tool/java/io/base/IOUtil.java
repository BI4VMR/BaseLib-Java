package net.bi4vmr.tool.java.io.base;

import java.io.Closeable;

/**
 * 输入与输出工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class IOUtil {

    /**
     * 释放资源。
     * <p>
     * 关闭实现了Closeable接口的资源，例如文件输入或输出流。
     * <p>
     * 如果遇到异常，会将错误信息输出到控制台上。
     *
     * @param closeable 需要关闭的Closeable资源。
     */
    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (Exception e) {
            System.err.println("Close failed! Reason:[" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    /**
     * 释放资源（静默）。
     * <p>
     * 关闭实现了Closeable接口的资源，例如文件输入或输出流。
     * <p>
     * 如果遇到异常，会将其忽略。
     *
     * @param closeable 需要关闭的Closeable资源。
     */
    public static void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (Exception e) {
            // 静默关闭，无需进一步处理。
        }
    }
}
