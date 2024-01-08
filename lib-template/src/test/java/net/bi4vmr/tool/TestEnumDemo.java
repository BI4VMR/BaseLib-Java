package net.bi4vmr.tool;

import net.bi4vmr.tool.baselib.java.EnumDemo;
import org.junit.jupiter.api.Test;

/**
 * Name        : TestEnumDemo
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-11-20 15:55
 * <p>
 * Description : Enum模板的测试类。
 */
public class TestEnumDemo {

    @Test
    void test() {
        /* 根据序号获得对应的枚举常量 */
        System.out.println("根据序号获得对应的枚举常量：");
        System.out.println(EnumDemo.valueOf(0));
        System.out.println(EnumDemo.valueOf(2));
        System.out.println(EnumDemo.valueOf(-1));

        /* 获取上一项 */
        System.out.println();
        System.out.println("获取上一项：");
        System.out.println(EnumDemo.ITEM_01.previous());
        System.out.println(EnumDemo.ITEM_02.previous());
        System.out.println(EnumDemo.ITEM_03.previous());

        /* 获取下一项 */
        System.out.println();
        System.out.println("获取下一项：");
        System.out.println(EnumDemo.ITEM_01.next());
        System.out.println(EnumDemo.ITEM_02.next());
        System.out.println(EnumDemo.ITEM_03.next());
    }
}
