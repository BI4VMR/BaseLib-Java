package net.bi4vmr.tool.java.common.base.file;

/**
 * 文件路径工具。
 * <p>
 * 解析文本形式的路径，目前仅支持 Unix 系平台路径。
 * <p>
 * 本工具旨在处理非本地的远程路径，对于本地路径请使用 {@link java.io.File} 等工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class FilePathUtil {

    private FilePathUtil() {
        // 静态工具类，无需实例化。
    }


    /**
     * 获取父目录。
     *
     * @param path 输入路径。
     * @return 父目录路径。
     */
    public static String getParent(String path) {
        // 将转义空格替换回普通空格
        String escapedPath = path.replace("\\ ", " ");
        int lastSlash = escapedPath.lastIndexOf("/");
        // 仅首部有斜杠，返回根目录。
        if (lastSlash == 0) return "/";
        // 没有斜杠，返回当前目录。
        if (lastSlash < 0) return ".";
        // 其他情况，返回首位到斜杠之前的内容。
        return escapedPath.substring(0, lastSlash);
    }

    /**
     * 获取文件名称。
     *
     * @param path 输入路径。
     * @return 文件名称。
     */
    public static String getFileName(String path) {
        // 将转义空格替换回普通空格
        String escapedPath = path.replace("\\ ", " ");
        int lastSlash = escapedPath.lastIndexOf("/");
        // 没有斜杠，整个路径即文件名。
        if (lastSlash < 0) return escapedPath;
        // 如果斜杠在末尾，说明路径以斜杠结尾，没有文件名，返回空字符串。
        if (lastSlash == escapedPath.length() - 1) return "";
        // 其他情况，返回斜杠后的内容。
        return escapedPath.substring(lastSlash + 1);
    }

    /**
     * 获取扩展名。
     *
     * @param path 输入路径。
     * @return 扩展名。若不存在则为空字符串。
     */
    public static String getExtension(String path) {
        // 将转义空格替换回普通空格
        String escapedPath = path.replace("\\ ", " ");
        int lastDot = escapedPath.lastIndexOf(".");
        // 未找到点，说明没有扩展名。
        if (lastDot < 0) return "";
        // 如果点在末尾，说明路径以点结尾，没有扩展名。
        if (lastDot == escapedPath.length() - 1) return "";
        // 其他情况，返回点后的内容。
        return escapedPath.substring(lastDot + 1);
    }

    /**
     * 获取文件名称（无扩展名）。
     *
     * @param path 输入路径。
     * @return 文件名称。
     */
    public static String getFileNameWithoutExtension(String path) {
        String fileName = getFileName(path);
        String extension = getExtension(path);
        return fileName.replaceFirst("\\." + extension + "$", "");
    }
}
