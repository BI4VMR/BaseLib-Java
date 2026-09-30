package net.bi4vmr.tool;

import net.bi4vmr.tool.java.template.base.EnumDemo;
import org.junit.jupiter.api.Test;

/**
 * Enum模板的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class TestEnumDemo {

    @Test
    void test() {
        /* 根据序号获得对应的枚举常量 */
        System.out.println("根据序号获得对应的枚举常量：");
        System.out.println(EnumDemo.parseFromIndex(0));
        System.out.println(EnumDemo.parseFromIndex(2));
        System.out.println(EnumDemo.parseFromIndex(-1));

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
