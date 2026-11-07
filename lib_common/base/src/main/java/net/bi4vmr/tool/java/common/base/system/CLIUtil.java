package net.bi4vmr.tool.java.common.base.system;

import net.bi4vmr.tool.java.common.base.general.TextUtil;
import net.bi4vmr.tool.java.common.base.io.BaseIOUtil;
import net.bi4vmr.tool.java.common.base.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 命令行工具。
 * <p>
 * 执行命令并获取输出消息或状态码。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class CLIUtil {

    /**
     * 日志工具。
     */
    private static final Logger logger = LoggerFactory.getLogger(CLIUtil.class);


    private CLIUtil() {
        // 静态工具类，无需实例化。
    }


    /**
     * 执行命令并返回进程实例。
     * <p>
     * 本方法只能处理拆分后的命令数组，例如： {@code "ipconfig", "/all" } ，对于完整的命令语句（例如： {@code "ipconfig /all" } ），
     * 请使用 {@link #run(String, boolean)} 方法。
     *
     * @param command       命令数组。
     * @param redirectError 是否将错误输出重定向到标准输出。
     * @return 已启动的子进程。
     * @throws IOException 进程启动失败。
     */
    public static Process run(String[] command, boolean redirectError) throws IOException {
        return new ProcessBuilder(command)
                .redirectErrorStream(redirectError)
                .start();
    }

    /**
     * 执行命令并返回进程实例。
     * <p>
     * 简化方法，将错误输出合并到标准输出，其他行为详见 {@link #run(String[], boolean)} 。
     *
     * @param command 命令数组。
     * @return 已启动的子进程。
     * @throws IOException 进程启动失败。
     */
    public static Process run(String[] command) throws IOException {
        return run(command, true);
    }

    /**
     * 执行命令并返回进程实例。
     * <p>
     * 本方法将对完整的命令语句（例如： {@code "ipconfig /all" } ）进行拆分，再传递给 {@link ProcessBuilder } 执行。
     *
     * @param command       命令语句。
     * @param redirectError 是否将错误输出重定向到标准输出。
     * @return 已启动的子进程。
     * @throws IOException 进程启动失败。
     */
    public static Process run(String command, boolean redirectError) throws IOException {
        return run(parseCommand(command), redirectError);
    }

    /**
     * 执行命令并返回进程实例。
     * <p>
     * 简化方法，将错误输出合并到标准输出，其他行为详见 {@link #run(String, boolean)} 。
     *
     * @param command 命令语句。
     * @return 已启动的子进程。
     * @throws IOException 进程启动失败。
     */
    public static Process run(String command) throws IOException {
        return run(command, true);
    }

    /**
     * 执行命令并返回状态码。
     * <p>
     * 本方法只能处理拆分后的命令数组，例如： {@code "ipconfig", "/all" } ，对于完整的命令语句（例如： {@code "ipconfig /all" } ），
     * 请使用 {@link #runForStatus(String)} 方法。
     *
     * @param command 命令数组。
     * @return 状态码。 {@code -1} 表示命令执行错误。
     * @see #runForStatus(String)
     */
    public static int runForStatus(String[] command) {
        int status = -1;

        try {
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start();

            // 不关心输出内容，但需要明确将它们丢弃，防止占用内存与死锁。
            BaseIOUtil.readAndDrop(process.getInputStream());

            status = process.waitFor();
        } catch (Exception e) {
            logger.error("Run command failed! Reason:[{}: {}]", e.getClass().getSimpleName(), e.getMessage());
            logger.error("Command:[{}]", String.join(" ", command));
        }

        return status;
    }

    /**
     * 执行命令并返回状态码。
     * <p>
     * 简化方法，先对完整的命令语句（例如： {@code "ipconfig /all" } ）进行拆分，再传递给 {@link ProcessBuilder } 执行，其他行为详见
     * {@link #run(String, boolean)} 。
     *
     * @param command 命令语句。
     * @return 状态码。若出现异常则返回 {@code -1}。
     */
    public static int runForStatus(String command) {
        return runForStatus(parseCommand(command));
    }

    /**
     * 执行命令并等待指定时长后返回状态码。
     * <p>
     * 本方法只能处理拆分后的命令数组，例如： {@code "ipconfig", "/all" } ，对于完整的命令语句（例如： {@code "ipconfig /all" } ），
     * 请使用 {@link #runForStatus(String, long, TimeUnit)} 方法。
     *
     * @param command 命令数组。
     * @param timeout 最大等待时长。
     * @param unit    等待时间单位。
     * @return 状态码。 {@code -1} 表示命令执行错误； {@code -2} 表示等待命令返回时线程被中断；{@code -3} 表示命令执行超时。
     */
    public static int runForStatus(String[] command, long timeout, TimeUnit unit) {
        int status = -1;

        try {
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start();

            // 不关心输出内容，但需要明确将它们丢弃，防止占用内存与死锁。
            BaseIOUtil.readAndDrop(process.getInputStream());

            boolean success = process.waitFor(timeout, unit);
            if (success) {
                status = process.exitValue();
            } else {
                status = -3;
            }
        } catch (InterruptedException e) {
            // 阻塞中的线程被中断时中断标志位不会置位，此处调用中断方法，确保外部观察者能够得到正确的线程状态。
            Thread.currentThread().interrupt();
            status = -2;
        } catch (Exception e) {
            logger.error("Run command failed! Reason:[{}: {}]", e.getClass().getSimpleName(), e.getMessage());
            logger.error("Command:[{}]", String.join(" ", command));
        }

        return status;
    }

    /**
     * 执行命令并返回状态码。
     * <p>
     * 简化方法，先对完整的命令语句（例如： {@code "ipconfig /all" } ）进行拆分，再传递给 {@link ProcessBuilder } 执行，其他行为详见
     * {@link #runForStatus(String[], long, TimeUnit)} 。
     *
     * @param command 命令语句。
     * @param timeout 最大等待时长。
     * @param unit    等待时间单位。
     * @return 状态码。 {@code -1} 表示命令执行错误； {@code -2} 表示等待命令返回时线程被中断；{@code -3} 表示命令执行超时。
     */
    public static int runForStatus(String command, long timeout, TimeUnit unit) {
        return runForStatus(parseCommand(command), timeout, unit);
    }

    /**
     * 判断命令是否执行成功。
     * <p>
     * 基于通用语义进行判断：状态码为 {@code 0} 时返回 {@code true} ，否则返回 {@code false} 。
     * <p>
     * 如果目标命令的状态码具有特殊含义，请勿使用该方法。
     *
     * @param status 状态码。
     * @return 执行结果。
     */
    public static boolean isSuccess(int status) {
        return status == 0;
    }

    /**
     * 判断命令是否执行失败。
     * <p>
     * 基于通用语义进行判断：状态码为 {@code 0} 时返回 {@code false} ，否则返回 {@code true} 。
     * <p>
     * 如果目标命令的状态码具有特殊含义，请勿使用该方法。
     *
     * @param status 状态码。
     * @return 执行结果。
     */
    public static boolean isFailure(int status) {
        return status != 0;
    }

    /**
     * 执行命令并获取文本消息。
     * <p>
     * 执行命令并阻塞当前线程，然后读取所有输出数据并转为文本。
     * <p>
     * 本方法只能处理拆分后的命令数组，例如： {@code "ipconfig", "/all" } ，对于完整的命令语句（例如： {@code "ipconfig /all" } ），
     * 请使用 {@link #runForLines(String, boolean, String)} 方法。
     * <p>
     * 解析输出文本时字符集的优先级为：参数指定 > JVM编码 > 默认值 (UTF-8) 。
     *
     * @param command       命令数组。
     * @param redirectError 是否将错误输出重定向到标准输出。
     * @param charset       字符集名称。空值表示自动侦测。
     * @return 命令输出文本。若出现异常则返回空值。
     */
    public static List<String> runForLines(String[] command, boolean redirectError, String charset) {
        try {
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(redirectError)
                    .start();
            // 阻塞直至所有输出都读取完毕
            List<String> result = BaseIOUtil.readAsLines(process.getInputStream(), IOUtil.BUFFER_SIZE_DEFAULT, selectCharset(charset));
            // 读取命令输出后，等待进程终止再返回内容。
            process.destroy();
            process.waitFor();
            return result;
        } catch (Exception e) {
            logger.error("Run command failed! Reason:[{}: {}]", e.getClass().getSimpleName(), e.getMessage());
            logger.error("Command:[{}]", String.join(" ", command));
            return null;
        }
    }

    /**
     * 执行命令并获取文本消息。
     * <p>
     * 执行命令并阻塞当前线程，然后读取所有输出数据并转为文本。
     * <p>
     * 简化方法，先对完整的命令语句（例如： {@code "ipconfig /all" } ）进行拆分，再传递给 {@link ProcessBuilder } 执行，其他行为详见
     * {@link #runForLines(String[], boolean, String)} 。
     *
     * @param command       命令语句。
     * @param redirectError 是否将错误输出重定向到标准输出。
     * @param charset       字符集名称。空值表示自动侦测。
     * @return 命令输出文本。若出现异常则返回空值。
     */
    public static List<String> runForLines(String command, boolean redirectError, String charset) {
        return runForLines(parseCommand(command), redirectError, charset);
    }

    /**
     * 执行命令并获取文本消息。
     * <p>
     * 执行命令并阻塞当前线程，将错误输出与标准输出合并，然后读取所有输出数据并转为文本。
     * <p>
     * 简化方法，先对完整的命令语句（例如： {@code "ipconfig /all" } ）进行拆分，再传递给 {@link ProcessBuilder } 执行，其他行为详见
     * {@link #runForLines(String[], boolean, String)} 。
     *
     * @param command 命令语句。
     * @return 命令输出文本。若出现异常则返回空值。
     */
    public static List<String> runForLines(String command) {
        return runForLines(parseCommand(command), true, null);
    }

    /**
     * 执行命令并获取文本消息。
     * <p>
     * 执行命令并阻塞当前线程，然后读取所有输出数据并转为文本。
     *
     * @param command       命令语句。
     * @param redirectError 是否将错误输出重定向到标准输出。
     * @param charset       字符集名称。空值表示自动侦测。
     * @return 命令输出文本。若出现异常则返回空值。
     */
    public static String runForText(String command, boolean redirectError, String charset) {
        List<String> lines = runForLines(command, redirectError, charset);
        return (lines == null) ? null : String.join("\n", lines);
    }

    /**
     * 执行命令并获取文本消息。
     * <p>
     * 执行命令并阻塞当前线程，然后读取所有输出数据并转为文本。
     *
     * @param command 命令语句。
     * @return 命令输出文本。若出现异常则返回空值。
     */
    public static String runForText(String command) {
        return runForText(command, true, null);
    }

    /**
     * 将完整的命令语句拆分为数组。
     * <p>
     * {@link ProcessBuilder} 只能接受已按空格拆分的命令数组，如果用户输入的是整行命令，需要先通过本方法进行拆分。
     * <p>
     * 本方法能够识别引号并将其移除，只保留其中的内容作为一个完整参数。例如： {@code echo "say \"hi\""} 将被解析为
     * {@code ["echo", "say", ""hi""]} 。
     *
     * @param command 命令语句。
     * @return 拆分后的命令数组。
     */
    public static String[] parseCommand(String command) {
        // 前置校验：输入无效时返回空命令。
        if (TextUtil.isBlank(command)) {
            return new String[]{""};
        }

        List<String> result = new ArrayList<>();
        StringBuilder stash = new StringBuilder();
        // 当前所处的引号类型，特殊值 `0` 表示不在引号内部。
        char quote = 0;
        // 当前引号之间的内容是否为空。
        boolean emptyQuote = false;

        // 遍历字符，拆分命令语句。
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            // 去掉双引号后，转义符号不再有意义，需要移除它们；单引号内的字符不作特殊处理。
            int nextIndex = i + 1;
            if ((c == '\\') && (quote != '\'') && nextIndex < command.length()) {
                char next = command.charAt(nextIndex);
                if (next == '"' || next == '\'' ||
                        next == '\\' || next == '$' || next == '?' ||
                        Character.isWhitespace(next)) {
                    stash.append(next);
                    // 后一位字符已被录入，此处进度额外推进一位。
                    i++;
                    continue;
                }
            }

            // 如果当前字符是引号，则进入或退出引号累计模式，并跳过后续操作，将引号之间的内容视为一个整体。
            boolean noQuote = (quote == 0);
            if ((c == '"' || c == '\'') && (noQuote || quote == c)) {
                // 记录 "" 或 '' 这类空参数。
                if (!noQuote && stash.length() == 0) {
                    emptyQuote = true;
                }

                quote = noQuote ? c : 0;
                continue;
            }

            // 如果当前字符是空格且不在引号内，则将暂存区记录的字符视作一个语句块并放入结果列表。
            if (Character.isWhitespace(c) && noQuote) {
                /*
                 * 若单词之间存在多个空格，此处需要跳过，防止产生多个内容为空的数组元素。
                 *
                 * 如果当前暂存区为空但已识别为空引号，则需要添加一个空参数，防止某些软件中参数移位导致出错。
                 */
                if (stash.length() == 0 && !emptyQuote) {
                    continue;
                }

                result.add(stash.toString());
                stash.setLength(0);
                emptyQuote = false;
            } else {
                // 如果当前字符是普通字符，则放入暂存区。
                stash.append(c);
            }
        }

        // 循环结束后标志位应当归零，否则说明存在未闭合的引号，返回空命令。
        if (quote != 0) {
            logger.error("Unclosed quote in command [{}]!", command);
            return new String[]{""};
        }

        // 上述循环只处理到最后一个空格之前的字符，此处需要将剩余字符放入结果列表。
        if (stash.length() > 0 || emptyQuote) {
            result.add(stash.toString());
        }

        return result.toArray(new String[0]);
    }

    /**
     * 判断当前环境是否存在可执行程序。
     *
     * @param name 程序名称。
     * @return `true` 表示程序存在； `false` 表示程序不存在。
     */
    public static boolean hasExecutable(String name) {
        String detectProgram = SystemUtil.isWindows() ? "where" : "which";
        int status = CLIUtil.runForStatus(detectProgram + " " + name);
        return CLIUtil.isSuccess(status);
    }

    /**
     * 终止进程。
     * <p>
     * 首先向进程发送终止信号，阻塞当前线程并等待最多 {@code timeout} 毫秒，如果该时长内目标进程仍未终止，则强制杀死进程。
     *
     * @param process 目标进程。
     * @param timeout 最大等待时长。
     * @return {@code true} 表示进程在最大等待时长内终止； {@code false} 表示进程在最大等待时长后仍未终止。
     */
    public static boolean stopProcess(Process process, long timeout) {
        if (process == null) {
            return true;
        }

        try {
            // 首先尝试正常终止进程
            process.destroy();

            // 等待至参数指定的时长
            boolean exited = process.waitFor(timeout, TimeUnit.MILLISECONDS);

            // 常规终止信号未响应时，发送强制终止信号。
            if (!exited) {
                logger.error("Failed to stop process, kill it!");
                process.destroyForcibly();
            }

            return exited;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while waiting for process destroy!");
            return false;
        }
    }

    /**
     * 终止进程。
     * <p>
     * 简化方法，最大等待3秒，其他行为详见 {@link #stopProcess(Process, long)} 。
     *
     * @param process 目标进程。
     * @return {@code true} 表示进程在最大等待时长内终止， {@code false} 表示进程在最大等待时长后仍未终止。
     */
    public static boolean stopProcess(Process process) {
        return stopProcess(process, 3 * 1000L);
    }

    /**
     * 选择解析输入流时所用的字符集。
     * <p>
     * 优先使用用户指定的字符集，否则侦测操作系统声明的控制台字符集；若无法侦测默认使用 {@link StandardCharsets#UTF_8} 。
     *
     * @param userset 用户指定的字符集，空值表示未指定。
     * @return 最终使用的字符集。
     */
    private static Charset selectCharset(String userset) {
        Charset charset = StandardCharsets.UTF_8;

        try {
            if (TextUtil.isNotBlank(userset)) {
                /* 存在用户输入，则尝试解析该字符集并进行使用。 */
                charset = Charset.forName(userset);
            } else {
                /* 不存在用户输入，尝试解析系统声明的控制台字符集。 */
                // Java 17引入的平台编码属性
                String nativeEncoding = System.getProperty("native.encoding");
                if (TextUtil.isNotBlank(nativeEncoding)) {
                    charset = Charset.forName(nativeEncoding);
                } else {
                    // 如果当前环境低于Java 17，则使用SUN提供的隐藏属性。
                    String internalEncoding = System.getProperty("sun.jnu.encoding");
                    charset = Charset.forName(internalEncoding);
                }
            }
        } catch (Exception e) {
            // 解析失败，保持默认值。
            logger.error("Invalid charset [{}], fallback to [UTF-8]!", userset);
        }

        return charset;
    }
}
