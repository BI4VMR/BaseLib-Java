package net.bi4vmr.tool.java.template.base;

/**
 * 枚举类模板。
 *
 * @author BI4VMR@outlook.com
 */
public enum EnumDemo {
    ITEM_01,
    ITEM_02,
    ITEM_03;

    /**
     * 获取上一项。
     * <p>
     * 获取当前常量在声明列表中的前一个常量。
     * <p>
     * 如果当前已经是第一项，则返回列表中的最后一项。
     *
     * @return 上一项。
     */
    public EnumDemo previous() {
        EnumDemo[] items = values();
        int maxOrdinal = items.length - 1;
        int itemOrdinal = ordinal();

        if (itemOrdinal == 0) {
            // 当前常量为第一项时，返回最后一项。
            return items[maxOrdinal];
        } else {
            // 当前常量不是第一项时，返回前一项。
            return items[itemOrdinal - 1];
        }
    }

    /**
     * 获取下一项。
     * <p>
     * 获取当前常量在声明列表中的后一个常量。
     * <p>
     * 如果当前已经是最后一项，则返回列表中的第一项。
     *
     * @return 下一项。
     */
    public EnumDemo next() {
        EnumDemo[] items = values();
        int maxOrdinal = items.length - 1;
        int itemOrdinal = ordinal();

        if (itemOrdinal < maxOrdinal) {
            // 当前常量的序号小于最后一项的序号时，返回后一项。
            return items[itemOrdinal + 1];
        } else {
            // 当前常量的序号等于最后一项的序号时，返回第一项。
            return items[0];
        }
    }

    /**
     * 根据序号获取枚举常量。
     * <p>
     * 根据序号获取对应的枚举常量。
     *
     * @param ordinal 枚举序号。
     * @return 枚举常量。如果传入参数没有对应的常量，则返回空值。
     */
    public static EnumDemo valueOf(int ordinal) {
        EnumDemo[] items = values();
        for (EnumDemo item : items) {
            if (item.ordinal() == ordinal) {
                return item;
            }
        }

        return null;
    }
}
