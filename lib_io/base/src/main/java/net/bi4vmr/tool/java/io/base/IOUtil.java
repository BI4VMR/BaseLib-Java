package net.bi4vmr.tool.java.io.base;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 输入与输出工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class IOUtil {

    /**
     * 默认的缓冲区大小。
     */
    private final static int DEFAULT_BUFFER_SIZE = 8 * 1024;

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


    /**
     * 从输入流读取所有数据并转为文本。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空字符串，默认使用8KB缓冲区、UTF-8编码。
     *
     * @param stream 输入流。
     * @return 文本。
     */
    public static String readAllAsText(InputStream stream) {
        return readAllAsText(stream, DEFAULT_BUFFER_SIZE, StandardCharsets.UTF_8);
    }

    /**
     * 从输入流读取所有数据并转为文本。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空字符串，默认使用8KB缓冲区。
     *
     * @param stream  输入流。
     * @param charset 字符集。
     * @return 文本。
     */
    public static String readAllAsText(InputStream stream, Charset charset) {
        return readAllAsText(stream, DEFAULT_BUFFER_SIZE, charset);
    }

    /**
     * 从输入流读取所有数据并转为文本。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空字符串，默认使用UTF-8编码。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小。
     * @return 文本。
     */
    public static String readAllAsText(InputStream stream, int bufferSize) {
        return readAllAsText(stream, bufferSize, StandardCharsets.UTF_8);
    }

    /**
     * 从输入流读取所有数据并转为文本。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空字符串。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小。
     * @param charset    字符集。
     * @return 文本。
     */
    public static String readAllAsText(InputStream stream, int bufferSize, Charset charset) {
        byte[] datas = readAllBytes(stream, bufferSize);
        return new String(datas, charset);
    }

    /**
     * 从输入流读取所有数据。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空数组；缓冲区容量默认为8KB。
     *
     * @param stream 输入流。
     * @return 字节数组。
     */
    public static byte[] readAllBytes(InputStream stream) {
        return readAllBytes(stream, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 从输入流读取所有数据。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 遇到异常时默认返回空数组。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小。
     * @return 字节数组。
     */
    public static byte[] readAllBytes(InputStream stream, int bufferSize) {
        byte[] empty = new byte[]{};

        // 校验输入参数的合法性
        if (bufferSize <= 0) {
            return empty;
        }

        try (
                BufferedInputStream bis = new BufferedInputStream(stream, bufferSize)
        ) {
            return bis.readAllBytes();
        } catch (IOException e) {
            System.err.println("Read file as bytes failed! Reason:[" + e.getMessage() + "]");
            e.printStackTrace();
        }

        return empty;
    }
}
