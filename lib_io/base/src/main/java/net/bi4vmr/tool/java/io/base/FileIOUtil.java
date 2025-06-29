package net.bi4vmr.tool.java.io.base;

import net.bi4vmr.tool.java.common.base.NumberUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 文件输入与输出工具。
 *
 * @author BI4VMR@outlook.com
 */
public class FileIOUtil extends IOUtil {

    /**
     * 读取二进制数据。
     * <p>
     * 该方法将从文件起始位置开始，读取最多{@link Integer#MAX_VALUE}字节的数据，文件体积较大时需要注意内存占用问题。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file 目标文件。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的数组。
     */
    public static byte[] readAsBytes(File file) {
        return readAsBytes(file, 0, Integer.MAX_VALUE);
    }

    /**
     * 读取二进制数据。
     * <p>
     * 该方法将从文件起始位置开始，读取第二参数"length"指定长度的数据。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file   目标文件。
     * @param length 读取字节数。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的数组。
     */
    public static byte[] readAsBytes(File file, int length) {
        return readAsBytes(file, 0, length);
    }

    /**
     * 读取二进制数据。
     * <p>
     * 该方法将从第二参数"offset"指定位置开始，读取第三参数"length"指定长度的数据。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file   目标文件。
     * @param offset 起始位置（从0开始计数）。
     * @param length 读取字节数。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的数组。
     */
    public static byte[] readAsBytes(File file, long offset, int length) {
        byte[] empty = new byte[]{};

        // 校验文件是否可读
        if (file == null || !file.exists() || file.isDirectory() || !file.canRead()) {
            return empty;
        }

        // 校验输入参数的合法性
        if (offset < 0 || offset >= file.length() || length < 0) {
            return empty;
        }

        // 如果参数指定的长度大于实际数据长度，则改写为实际数据长度。
        long maxLength = file.length() - offset;
        if (length > maxLength) {
            // 输入参数为"int"类型，已确认数值大于"long"类型值，因此"long"类型值必然在"int"范围内，可以安全地窄化转换。
            length = (int) maxLength;
        }

        byte[] buffer = new byte[length];
        RandomAccessFile accessor = null;
        try {
            accessor = new RandomAccessFile(file, "r");
            accessor.seek(offset);
            int count = accessor.read(buffer);
            // 如果实际读取的数据长度小于目标长度，则截取有效元素。
            if (count < length) {
                buffer = Arrays.copyOf(buffer, count);
            }

            return buffer;
        } catch (IOException e) {
            System.err.println("Read file as bytes failed! Reason:[" + e.getMessage() + "]");
            e.printStackTrace();
        } finally {
            IOUtil.closeSilently(accessor);
        }

        return empty;
    }

    /**
     * 读取十六进制文本。
     * <p>
     * 该方法将从文件起始位置开始，读取最多{@link Integer#MAX_VALUE}字节的数据，文件体积较大时需要注意内存占用问题。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file 目标文件。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的字符串。
     */
    public static String readAsHexText(File file) {
        return readAsHexText(file, 0, Integer.MAX_VALUE);
    }

    /**
     * 读取十六进制文本。
     * <p>
     * 该方法将从文件起始位置开始，读取第二参数"length"指定长度的数据，并转换为十六进制文本。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file   目标文件。
     * @param length 读取字节数。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的字符串。
     */
    public static String readAsHexText(File file, int length) {
        return readAsHexText(file, 0, length);
    }

    /**
     * 读取十六进制文本。
     * <p>
     * 该方法将从第二参数"offset"指定位置开始，读取第三参数"length"指定长度的数据，并转换为十六进制文本。
     * <p>
     * 该方法仅适用于简单数据的处理，无法处理长度超过2GiB的部分。这是因为数组容量受到"int"类型最大值的限制，并且单次读取过多数据也容易导
     * 致内存溢出。对于此类需求，调用者可以分块读取文件并进行处理。
     *
     * @param file   目标文件。
     * @param offset 起始位置（从0开始计数）。
     * @param length 读取字节数。
     * @return 二进制数据。永不为空值，读取失败时将返回内容为空的字符串。
     */
    public static String readAsHexText(File file, int offset, int length) {
        byte[] datas = readAsBytes(file, offset, length);
        return NumberUtil.toHexString(datas);
    }
}
