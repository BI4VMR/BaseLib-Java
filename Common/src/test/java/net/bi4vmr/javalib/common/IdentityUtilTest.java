package net.bi4vmr.javalib.common;

import org.junit.jupiter.api.Test;

/**
 * Name        : TextUtilTest
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@qq.com
 * <p>
 * Date        : 2022-11-20 00:05
 * <p>
 * Description : IdentityUtil的测试类。
 */
public class IdentityUtilTest {

    @Test
    void test() {
        System.out.println("UUID:" + IdentityUtil.genUUID());
        System.out.println("UpperUUID:" + IdentityUtil.genUUIDUpper());
        System.out.println("PureUUID:" + IdentityUtil.genUUIDPure());
        System.out.println("PureAndUpperUUID:" + IdentityUtil.genUUIDPureUpper());
    }
}
