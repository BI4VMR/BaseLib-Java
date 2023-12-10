package net.bi4vmr.tool;

import net.bi4vmr.tool.baselib.java.identity.IdentityUtil;
import org.junit.jupiter.api.Test;

/**
 * Name        : TestIdentityUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-20 00:05
 * <p>
 * Description : IdentityUtil的测试类。
 */
public class TestIdentityUtil {

    @Test
    void test() {
        System.out.println("UUID:" + IdentityUtil.genUUID());
        System.out.println("UpperUUID:" + IdentityUtil.genUUIDUpper());
        System.out.println("PureUUID:" + IdentityUtil.genUUIDPure());
        System.out.println("PureAndUpperUUID:" + IdentityUtil.genUUIDPureUpper());
    }
}
