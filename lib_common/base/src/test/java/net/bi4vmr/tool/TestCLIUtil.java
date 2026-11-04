package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.system.CLIUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * CLIUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestCLIUtil {

    private static void check(String command, String... expected) {
        assertArrayEquals(expected, CLIUtil.parseCommand(command), command);
    }

    @Test
    void parsePlain() {
        check("ls -l /tmp", "ls", "-l", "/tmp");
        check("  ls \t -l   /tmp  ", "ls", "-l", "/tmp");
        check("", "");
        check(null, "");
    }

    @Test
    void parseQuotes() {
        check("echo \"Hello World!\"", "echo", "Hello World!");
        check("echo \"it's\"", "echo", "it's");
        check("echo 'Hello World!'", "echo", "Hello World!");
        check("echo 'say \"hi\"'", "echo", "say \"hi\"");
        check("echo 'a\\'", "echo", "a\\");
    }

    @Test
    void parseEscapedQuotes() {
        check("echo \\\"quoted\\\"", "echo", "\"quoted\"");
        check("echo \"a \\\"b\\\" c\"", "echo", "a \"b\" c");
        check("konsole -e /bin/zsh -ic \"echo \\\"Hello World\\!\\\"; exec $SHELL;\"",
                "konsole", "-e", "/bin/zsh", "-ic", "echo \"Hello World\\!\"; exec $SHELL;");
    }

    @Test
    void parseWindowsPath() {
        check("dir C:\\Windows\\System32", "dir", "C:\\Windows\\System32");
        check("\"C:\\Program Files\\app.exe\" /all", "C:\\Program Files\\app.exe", "/all");
    }

    @Test
    void parseUnclosed() {
        check("echo \"abc", "");
        check("echo 'abc", "");
        check("echo it\\'s", "echo", "it's");
    }
}
