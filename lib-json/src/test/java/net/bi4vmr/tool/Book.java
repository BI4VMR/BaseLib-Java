package net.bi4vmr.tool;

/**
 * Name        : net.bi4vmr.tool.Book
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@qq.com
 * <p>
 * Date        : 2022-11-20 15:58
 * <p>
 * Description : 测试实体类 - 书籍。
 */
public class Book {

    private String name;
    private double price;

    public Book() {
        // 默认构造方法
    }

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "net.bi4vmr.tool.Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
