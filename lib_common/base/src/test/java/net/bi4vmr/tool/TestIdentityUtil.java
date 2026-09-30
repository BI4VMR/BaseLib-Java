package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.IdentityUtil;
import org.junit.jupiter.api.Test;

/**
 * IdentityUtil的测试类。
 *
 * @author BI4VMR@outlook.com
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
