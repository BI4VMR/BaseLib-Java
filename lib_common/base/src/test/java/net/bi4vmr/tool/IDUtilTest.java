package net.bi4vmr.tool;

import net.bi4vmr.tool.java.common.base.id.IDUtil;
import org.junit.jupiter.api.Test;

/**
 * IdentityUtil的测试类。
 *
 * @author bi4vmr@outlook.com
 */
public class IDUtilTest {

    @Test
    void test() {
        System.out.println("UUID:" + IDUtil.GUIDv4(false, false));
        System.out.println("UpperUUID:" + IDUtil.GUIDv4(false, true));
        System.out.println("PureUUID:" + IDUtil.GUIDv4(true, false));
        System.out.println("PureAndUpperUUID:" + IDUtil.GUIDv4());
    }
}
