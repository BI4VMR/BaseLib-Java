package net.bi4vmr.tool.java.finance.base;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 贷款计算工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class LoanUtil {

    // 除法运算的默认精度
    private static final int DIVIDE_SCALE = 10;

    /**
     * 计算等额本金还款计划。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @return {@link LoanMonthlyInfo} 列表；包含每期应当还款的本金与利息。
     * @throws NumberFormatException 如果总金额或年利率无法被转换为BigDecimal，则会抛出此异常。
     */
    public static List<LoanMonthlyInfo> getPlanOfLinearUnsafe(String totalAmount, String rateOfYear, int months)
            throws NumberFormatException {
        BigDecimal total = new BigDecimal(totalAmount);
        BigDecimal rate = (new BigDecimal(rateOfYear)).divide(new BigDecimal("100"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月待还的本金（等额本金每期所还的本金数量是相同的，因此仅需计算一次即可。）
        BigDecimal principal = total.divide(new BigDecimal(months), DIVIDE_SCALE, RoundingMode.HALF_UP);
        // 计算月利率
        BigDecimal rateOfMonth = rate.divide(new BigDecimal("12"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月待还的利息
        List<LoanMonthlyInfo> plan = new ArrayList<>();
        for (int month = 1; month <= months; month++) {
            // 当月利息 = ( 贷款总额 - 已还本金总额 ) * 月利率
            BigDecimal remainingAmount = total.subtract(principal.multiply(new BigDecimal(month - 1)));
            BigDecimal interest = remainingAmount.multiply(rateOfMonth);
            // 当月还款总额 = 本金 + 利息
            BigDecimal monthlyPayment = principal.add(interest);

            LoanMonthlyInfo info = new LoanMonthlyInfo(
                    month,
                    principal.setScale(2, RoundingMode.HALF_UP).toString(),
                    interest.setScale(2, RoundingMode.HALF_UP).toString(),
                    monthlyPayment.setScale(2, RoundingMode.HALF_UP).toString()
            );
            plan.add(info);
        }

        return plan;
    }

    /**
     * 计算等额本金还款计划。
     * <p>
     * 如果总金额或年利率无法被转换为BigDecimal，则会返回空列表。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @return {@link LoanMonthlyInfo} 列表；包含每期应当还款的本金与利息。
     */
    public static List<LoanMonthlyInfo> getPlanOfLinear(String totalAmount, String rateOfYear, int months) {
        try {
            return getPlanOfLinearUnsafe(totalAmount, rateOfYear, months);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 计算指定期数的等额本金还款信息。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @param targetMonth 目标月数。
     * @return 目标月份的 {@link LoanMonthlyInfo} 实例；包含当期应当还款的本金与利息。
     * @throws NumberFormatException 如果总金额或年利率无法被转换为BigDecimal，则会抛出此异常。
     */
    public static LoanMonthlyInfo getTargetOfLinearUnsafe(String totalAmount, String rateOfYear, int months, int targetMonth)
            throws NumberFormatException {
        // 判断目标月份是否在有效范围内
        if (targetMonth < 1 || targetMonth > months) {
            throw new NumberFormatException("Target month is out of range!");
        }

        BigDecimal total = new BigDecimal(totalAmount);
        BigDecimal rate = (new BigDecimal(rateOfYear)).divide(new BigDecimal("100"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月待还的本金（等额本金每期所还的本金数量是相同的，因此仅需计算一次即可。）
        BigDecimal principal = total.divide(new BigDecimal(months), DIVIDE_SCALE, RoundingMode.HALF_UP);
        // 计算月利率
        BigDecimal rateOfMonth = rate.divide(new BigDecimal("12"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 当月利息 = ( 贷款总额 - 已还本金总额 ) * 月利率
        BigDecimal remainingAmount = total.subtract(principal.multiply(new BigDecimal(targetMonth - 1)));
        BigDecimal interest = remainingAmount.multiply(rateOfMonth);
        // 当月还款总额 = 本金 + 利息
        BigDecimal monthlyPayment = principal.add(interest);

        return new LoanMonthlyInfo(
                targetMonth,
                principal.setScale(2, RoundingMode.HALF_UP).toString(),
                interest.setScale(2, RoundingMode.HALF_UP).toString(),
                monthlyPayment.setScale(2, RoundingMode.HALF_UP).toString()
        );
    }

    /**
     * 计算指定期数的等额本金还款信息。
     * <p>
     * 如果总金额或年利率无法被转换为BigDecimal，或目标月数超出范围，则会返回金额为 `0` 的实例。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @param targetMonth 目标月数。
     * @return 目标月份的 {@link LoanMonthlyInfo} 实例；包含当期应当还款的本金与利息。
     */
    public static LoanMonthlyInfo getTargetOfLinear(String totalAmount, String rateOfYear, int months, int targetMonth) {
        try {
            return getTargetOfLinearUnsafe(totalAmount, rateOfYear, months, targetMonth);
        } catch (Exception e) {
            return new LoanMonthlyInfo(targetMonth, "0", "0", "0");
        }
    }

    /**
     * 计算等额本息还款计划。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @return {@link LoanMonthlyInfo} 列表；包含每期应当还款的本金与利息。
     * @throws NumberFormatException 如果总金额或年利率无法被转换为BigDecimal，则会抛出此异常。
     */
    public static List<LoanMonthlyInfo> getPlanOfAnnuityUnsafe(String totalAmount, String rateOfYear, int months)
            throws NumberFormatException {
        BigDecimal total = new BigDecimal(totalAmount);
        BigDecimal rate = (new BigDecimal(rateOfYear)).divide(new BigDecimal("100"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算月利率
        BigDecimal rateOfMonth = rate.divide(new BigDecimal("12"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月还款总额 = 贷款总额 * ( 月利率 * (1 + 月利率) ^ 贷款月数 / ( (1 + 月利率) ^ 贷款月数 - 1 ) )
        BigDecimal ci = (BigDecimal.ONE.add(rateOfMonth)).pow(months, MathContext.DECIMAL64);
        BigDecimal monthlyPayment = total.multiply(rateOfMonth).multiply(ci)
                .divide(ci.subtract(BigDecimal.ONE), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月待还的利息
        List<LoanMonthlyInfo> plan = new ArrayList<>();
        for (int month = 1; month <= months; month++) {
            // 当月利息 = 剩余金额 * 月利率
            BigDecimal interest = total.multiply(rateOfMonth);
            // 当月本金 = 当月还款总额 - 当月利息
            BigDecimal principal = monthlyPayment.subtract(interest);

            // 更新剩余总额
            total = total.subtract(principal);

            LoanMonthlyInfo info = new LoanMonthlyInfo(
                    month,
                    principal.setScale(2, RoundingMode.HALF_UP).toString(),
                    interest.setScale(2, RoundingMode.HALF_UP).toString(),
                    monthlyPayment.setScale(2, RoundingMode.HALF_UP).toString()
            );
            plan.add(info);
        }

        return plan;
    }

    /**
     * 计算等额本息还款计划。
     * <p>
     * 如果总金额或年利率无法被转换为BigDecimal，则会返回空列表。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @return {@link LoanMonthlyInfo} 列表；包含每期应当还款的本金与利息。
     */
    public static List<LoanMonthlyInfo> getPlanOfAnnuity(String totalAmount, String rateOfYear, int months) {
        try {
            return getPlanOfAnnuityUnsafe(totalAmount, rateOfYear, months);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 计算指定期数的等额本息还款信息。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @param targetMonth 目标月数。
     * @return 目标月份的 {@link LoanMonthlyInfo} 实例；包含当期应当还款的本金与利息。
     * @throws NumberFormatException 如果总金额或年利率无法被转换为BigDecimal，则会抛出此异常。
     */
    public static LoanMonthlyInfo getTargetOfAnnuityUnsafe(String totalAmount, String rateOfYear, int months, int targetMonth)
            throws NumberFormatException {
        // 判断目标月份是否在有效范围内
        if (targetMonth < 1 || targetMonth > months) {
            throw new NumberFormatException("Target month is out of range!");
        }

        BigDecimal total = new BigDecimal(totalAmount);
        BigDecimal rate = (new BigDecimal(rateOfYear)).divide(new BigDecimal("100"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算月利率
        BigDecimal rateOfMonth = rate.divide(new BigDecimal("12"), DIVIDE_SCALE, RoundingMode.HALF_UP);

        // 计算每月还款总额 = 贷款总额 * ( 月利率 * (1 + 月利率) ^ 贷款月数 / ( (1 + 月利率) ^ 贷款月数 - 1 ) )
        BigDecimal ci = (BigDecimal.ONE.add(rateOfMonth)).pow(months, MathContext.DECIMAL64);
        BigDecimal monthlyPayment = total.multiply(rateOfMonth).multiply(ci)
                .divide(ci.subtract(BigDecimal.ONE), DIVIDE_SCALE, RoundingMode.HALF_UP);

        BigDecimal interest = BigDecimal.ZERO;
        BigDecimal principal = BigDecimal.ZERO;

        // 计算每月待还的利息
        for (int month = 1; month <= targetMonth; month++) {
            // 当月利息 = 剩余金额 * 月利率
            interest = total.multiply(rateOfMonth);
            // 当月本金 = 当月还款总额 - 当月利息
            principal = monthlyPayment.subtract(interest);

            // 更新剩余总额
            total = total.subtract(principal);
        }

        return new LoanMonthlyInfo(
                targetMonth,
                principal.setScale(2, RoundingMode.HALF_UP).toString(),
                interest.setScale(2, RoundingMode.HALF_UP).toString(),
                monthlyPayment.setScale(2, RoundingMode.HALF_UP).toString()
        );
    }

    /**
     * 计算指定期数的等额本息还款信息。
     * <p>
     * 如果总金额或年利率无法被转换为BigDecimal，或目标月数超出范围，则会返回金额为 `0` 的实例。
     *
     * @param totalAmount 贷款金额。
     * @param rateOfYear  年利率百分比，例如：3%请填入"3.0"，不需要转换为"0.03"。
     * @param months      贷款月数。
     * @param targetMonth 目标月数。
     * @return 目标月份的 {@link LoanMonthlyInfo} 实例；包含当期应当还款的本金与利息。
     */
    public static LoanMonthlyInfo getTargetOfAnnuity(String totalAmount, String rateOfYear, int months, int targetMonth) {
        try {
            return getTargetOfAnnuityUnsafe(totalAmount, rateOfYear, months, targetMonth);
        } catch (Exception e) {
            return new LoanMonthlyInfo(targetMonth, "0", "0", "0");
        }
    }
}
