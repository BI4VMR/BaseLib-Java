package net.bi4vmr.tool.java.finance.base;

import java.util.Objects;

/**
 * 贷款每月还款计划。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class LoanMonthlyInfo {

    /**
     * 当期月数。
     */
    private final int month;

    /**
     * 当期本金。
     */
    private final String principal;

    /**
     * 当期利息。
     */
    private final String interest;

    /**
     * 应还总额。
     */
    private final String total;

    /**
     * 构造方法。
     *
     * @param month     当期月数。
     * @param principal 当期本金。
     * @param interest  当期利息。
     * @param total     应还总额。
     */
    public LoanMonthlyInfo(int month, String principal, String interest, String total) {
        this.month = month;
        this.principal = principal;
        this.interest = interest;
        this.total = total;
    }

    /**
     * 获取当期月数。
     *
     * @return 当期月数。
     */
    public int getMonth() {
        return month;
    }

    /**
     * 获取当期本金。
     *
     * @return 当期本金（单位：元）。
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 获取当期利息。
     *
     * @return 当期利息（单位：元）。
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 获取应还总额。
     *
     * @return 应还总额（单位：元）。
     */
    public String getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (hashCode() != o.hashCode()) return false;

        LoanMonthlyInfo that = (LoanMonthlyInfo) o;
        return month == that.month &&
                Objects.equals(principal, that.principal) &&
                Objects.equals(interest, that.interest) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, principal, interest, total);
    }

    @Override
    public String toString() {
        return "LoanMonthlyInfo{" +
                "month=" + month +
                ", principal='" + principal + '\'' +
                ", interest='" + interest + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
