package net.bi4vmr.tool.java.io.base;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 基本输入与输出工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class BaseIOUtil extends IOUtil {

    /**
     * 默认的缓冲区大小。
     */
    private final static int DEFAULT_BUFFER_SIZE = 8 * 1024;

    /**
     * 常用缓冲区大小：8KB。
     */
    public final static int BUFFER_SIZE_8KB = 8 * 1024;

    /**
     * 常用缓冲区大小：128KB。
     */
    public final static int BUFFER_SIZE_128KB = 128 * 1024;

    /**
     * 常用缓冲区大小：256KB。
     */
    public final static int BUFFER_SIZE_256KB = 256 * 1024;

    /**
     * 常用缓冲区大小：512KB。
     */
    public final static int BUFFER_SIZE_512KB = 512 * 1024;

    /**
     * 常用缓冲区大小：1MB。
     */
    public final static int BUFFER_SIZE_1MB = 1024 * 1024;

    /**
     * 常用缓冲区大小：4MB。
     */
    public final static int BUFFER_SIZE_4MB = 4 * 1024 * 1024;

    /**
     * 常用缓冲区大小：8MB。
     */
    public final static int BUFFER_SIZE_8MB = 8 * 1024 * 1024;

    /**
     * 常用缓冲区大小：32MB。
     */
    public final static int BUFFER_SIZE_32MB = 32 * 1024 * 1024;


    /**
     * 从输入流读取所有数据并转为文本。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空字符串，默认使用8KB缓冲区、UTF-8编码。
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
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空字符串，默认使用8KB缓冲区。
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
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空字符串，默认使用UTF-8编码。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小（字节）。
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
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空字符串。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小（字节）。
     * @param charset    字符集。
     * @return 文本。
     */
    public static String readAllAsText(InputStream stream, int bufferSize, Charset charset) {
        final String empty = "";

        // 校验输入参数的合法性
        if (bufferSize <= 0) {
            return empty;
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (
                InputStreamReader isr = new InputStreamReader(stream, charset);
                BufferedReader reader = new BufferedReader(isr, bufferSize)
        ) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            System.err.println("Read file as text failed! Reason:[" + e.getMessage() + "]");
            e.printStackTrace();
        }

        return empty;
    }

    /**
     * 从输入流读取所有数据。
     * <p>
     * 一次性读取流中的所有数据，只适合数据量已知且较小的流，否则会导致内存溢出。
     * <p>
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空数组；缓冲区容量默认为8KB。
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
     * 操作完毕后输入流将被关闭；遇到异常时默认返回空数组。
     *
     * @param stream     输入流。
     * @param bufferSize 缓冲区大小（字节）。
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

    /**
     * 将输入流中的数据保存至文件。
     * <p>
     * 操作完毕后输入流与目标文件流都会被关闭；缓冲区容量默认为8KB。
     *
     * @param stream 输入流。
     * @param dest   目标文件。
     */
    public static void copyToFile(InputStream stream, File dest) {
        copyToFile(stream, dest, DEFAULT_BUFFER_SIZE);
    }

    /**
     * 将输入流中的数据保存至文件。
     * <p>
     * 操作完毕后输入流与目标文件流都会被关闭。
     *
     * @param stream     输入流。
     * @param dest       目标文件。
     * @param bufferSize 缓冲区大小（字节）。
     */
    public static void copyToFile(InputStream stream, File dest, int bufferSize) {
        // 校验输入参数的合法性
        if (bufferSize <= 0) {
            return;
        }

        try (
                BufferedInputStream bis = new BufferedInputStream(stream, bufferSize);
                FileOutputStream fos = new FileOutputStream(dest)
        ) {
            byte[] buffer = new byte[bufferSize];
            while (true) {
                int count = bis.read(buffer);
                if (count == -1) {
                    break;
                }
                fos.write(buffer, 0, count);
            }
        } catch (IOException e) {
            System.err.println("Read file as bytes failed! Reason:[" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }
}
