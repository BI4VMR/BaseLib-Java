package net.bi4vmr.javalib.common;

import org.junit.jupiter.api.Test;

/**
 * Name        : DateTimeUtilTest
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-09-24 20:44
 * Description : DateTimeUtil的测试类。
 */
public class DateTimeUtilTest {

    @Test
    void test() {
        System.out.println(DateTimeUtil.getDateTimeText());
        System.out.println(DateTimeUtil.getDateText());
        System.out.println(DateTimeUtil.getTimeText());
    }
}
