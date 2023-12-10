package net.bi4vmr.tool;

import net.bi4vmr.tool.baselib.java.time.DateTimeUtil;
import org.junit.jupiter.api.Test;

/**
 * Name        : TestDateTimeUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-20 00:05
 * <p>
 * Description : DateTimeUtil的测试类。
 */
public class TestDateTimeUtil {

    @Test
    void test() {
        System.out.println(DateTimeUtil.getDateTimeText());
        System.out.println(DateTimeUtil.getDateText());
        System.out.println(DateTimeUtil.getTimeText());
    }
}
