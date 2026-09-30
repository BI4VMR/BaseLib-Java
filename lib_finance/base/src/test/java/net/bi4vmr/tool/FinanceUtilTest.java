package net.bi4vmr.tool;

import net.bi4vmr.tool.java.finance.base.FinanceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * {@link FinanceUtil} 功能测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class FinanceUtilTest {

    @Test
    public void test_DecimalToLongCent() {
        System.out.println("----- Test DecimalToLongCent start -----");

        BigDecimal decimal1 = new BigDecimal("1");
        long cent1 = FinanceUtil.DecimalToLongCent(decimal1);
        Assertions.assertEquals(100, cent1);

        BigDecimal decimal2 = new BigDecimal("1.1");
        long cent2 = FinanceUtil.DecimalToLongCent(decimal2);
        Assertions.assertEquals(110, cent2);

        BigDecimal decimal3 = new BigDecimal("1.11");
        long cent3 = FinanceUtil.DecimalToLongCent(decimal3);
        Assertions.assertEquals(111, cent3);

        // 原始数值精度大于两位小数时，应当抛出异常。
        BigDecimal decimal4 = new BigDecimal("1.111");
        Assertions.assertThrows(ArithmeticException.class, () -> FinanceUtil.DecimalToLongCent(decimal4));

        System.out.println("----- Test DecimalToLongCent end -----");
    }

    @Test
    public void test_LongCentToDecimal() {
        System.out.println("----- Test test_LongCentToDecimal start -----");

        // 1元
        long cent1 = 100;
        BigDecimal decimal1 = FinanceUtil.LongCentToDecimal(cent1);
        Assertions.assertEquals(0, BigDecimal.ONE.compareTo(decimal1));

        System.out.println("----- Test test_LongCentToDecimal end -----");
    }
}
