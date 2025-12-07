package net.bi4vmr.tool.java.finance.base;

/**
 * 贷款每月还款计划。
 *
 * @param month     当期月数。
 * @param principal 当期本金。
 * @param interest  当期利息。
 * @param total     应还总额。
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public record LoanMonthlyInfo(int month,
                              String principal,
                              String interest,
                              String total) {
}
