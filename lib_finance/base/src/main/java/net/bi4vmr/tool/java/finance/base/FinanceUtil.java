package net.bi4vmr.tool.java.finance.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 通用金融工具。
 *
 * @author BI4VMR@outlook.com
 * @since 1.0.0
 */
public class FinanceUtil {

    /**
     * 将元制 {@code BigDecimal} 转换为分制整数。
     * <p>
     * 将原始数值的小数点右移两位，得到以“分”为单位的整数。
     *
     * @param input 元制 {@code BigDecimal} 实例。
     * @return 长整型分制金额。
     * @throws ArithmeticException 若原始数据精度大于两位小数，则抛出异常提醒开发者，由开发者自行选择舍入或截断等策略，避免默认舍入
     *                             导致隐蔽的精度丢失错误。
     */
    public static long DecimalToLongCent(BigDecimal input) {
        // 要求原始数据精度不得大于两位小数
        return input.setScale(2, RoundingMode.UNNECESSARY)
                // 将小数点右移两位，得到以“分”为单位的整数。
                .movePointRight(2)
                .longValueExact();
    }

    /**
     * 将分制整数转换为元制 {@code BigDecimal}。
     * <p>
     * 将原始数值的小数点左移两位，得到以“元”为单位的 {@code BigDecimal} 实例。
     *
     * @param input 分制整数。
     * @return 元制 {@code BigDecimal} 实例。
     */
    public static BigDecimal LongCentToDecimal(long input) {
        return BigDecimal.valueOf(input, 2);
    }
}
