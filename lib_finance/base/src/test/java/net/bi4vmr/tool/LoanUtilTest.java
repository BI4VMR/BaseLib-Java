package net.bi4vmr.tool;

import net.bi4vmr.tool.java.finance.base.LoanMonthlyInfo;
import net.bi4vmr.tool.java.finance.base.LoanUtil;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * {@link LoanUtil} 功能测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class LoanUtilTest {

    @Test
    public void test_GetPlanOfLinear() {
        System.out.println("----- Test GetPlanOfLinear start -----");

        List<LoanMonthlyInfo> plan = LoanUtil.getPlanOfLinear("500000", "3", 300);
        plan.forEach(info -> {
            System.out.printf("第%d个月：还款 %s 元 (本金 %s 元 + 利息 %s 元)%n",
                    info.getMonth(), info.getTotal(), info.getPrincipal(), info.getInterest());
        });

        System.out.println("----- Test GetPlanOfLinear end -----");
    }

    @Test
    public void test_GetTargetOfLinear() {
        System.out.println("----- Test GetTargetOfLinear start -----");

        LoanMonthlyInfo info = LoanUtil.getTargetOfLinear("500000", "3", 300, 5);
        System.out.printf("第%d个月：还款 %s 元 (本金 %s 元 + 利息 %s 元)%n",
                info.getMonth(), info.getTotal(), info.getPrincipal(), info.getInterest());

        System.out.println("----- Test GetTargetOfLinear end -----");
    }

    @Test
    public void test_GetPlanOfAnnuity() {
        System.out.println("----- Test GetPlanOfAnnuity start -----");

        List<LoanMonthlyInfo> plan2 = LoanUtil.getPlanOfAnnuity("500000", "2.6", 360);
        plan2.forEach(info -> {
            System.out.printf("第%d个月：还款 %s 元 (本金 %s 元 + 利息 %s 元)%n",
                    info.getMonth(), info.getTotal(), info.getPrincipal(), info.getInterest());
        });

        System.out.println("----- Test GetPlanOfAnnuity end -----");
    }

    @Test
    public void test_GetTargetOfAnnuity() {
        System.out.println("----- Test GetTargetOfAnnuity start -----");

        LoanMonthlyInfo info = LoanUtil.getTargetOfAnnuity("500000", "2.6", 360, 5);
        System.out.printf("第%d个月：还款 %s 元 (本金 %s 元 + 利息 %s 元)%n",
                info.getMonth(), info.getTotal(), info.getPrincipal(), info.getInterest());

        System.out.println("----- Test GetTargetOfAnnuity end -----");
    }
}
