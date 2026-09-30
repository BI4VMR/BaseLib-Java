package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.DateTimeUtil;
import org.junit.jupiter.api.Test;

/**
 * DateTimeUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestDateTimeUtil {

    @Test
    void test() {
        System.out.println(DateTimeUtil.getDateTimeText());
        System.out.println(DateTimeUtil.getDateText());
        System.out.println(DateTimeUtil.getTimeText());
    }
}
