package net.bi4vmr.tool;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.bi4vmr.tool.java.text.gson.GsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * GsonUtil的测试类。
 *
 * @author BI4VMR@outlook.com
 */
public class GsonUtilTest {

    @Test
    void test() {
        /*
         * ---------- 单个对象测试 ----------
         */
        System.out.println("-----单个对象测试-----");

        Book book1 = new Book("Java编程思想", 49.9);
        System.out.println("Java对象转JSON对象: " + GsonUtil.toJSONObject(book1));
        System.out.println("Java对象转JSON文本: " + GsonUtil.toText(book1));
        System.out.println();

        String objectText = GsonUtil.toText(book1);
        System.out.println("JSON文本转Java对象: " + GsonUtil.toInstance(objectText, Book.class));
        System.out.println("JSON文本转JSON对象: " + GsonUtil.toJSONObject(objectText));
        System.out.println();

        JsonObject json = GsonUtil.toJSONObject(book1);
        System.out.println("JSON对象转JSON文本: " + GsonUtil.toText(json));
        System.out.println("JSON对象转Java对象: " + GsonUtil.toInstance(json, Book.class));
        System.out.println();

        /*
         * ---------- 数组测试 ----------
         */
        System.out.println("-----数组测试-----");

        System.out.println("-----实体类列表-----");

        Book book2 = new Book("易学Python", 75.0);
        Book book3 = new Book("IPSec VPN", 59.0);
        List<Book> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);

        System.out.println("实体类列表转JSON数组: " + GsonUtil.entitysToJSONArray(list));
        System.out.println("实体类列表转JSON文本: " + GsonUtil.toText(list));
        System.out.println();

        String arrayText = GsonUtil.toText(list);
        System.out.println("JSON文本转JSON数组: " + GsonUtil.toJSONArray(arrayText));
        System.out.println("JSON文本转实体类列表: " + GsonUtil.toEntityList(arrayText, Book.class));
        System.out.println();

        JsonArray array = GsonUtil.toJSONArray(arrayText);
        System.out.println("JSON数组转JSON文本: " + GsonUtil.toText(array));
        System.out.println("JSON数组转实体类列表: " + GsonUtil.toEntityList(array, Book.class));
        System.out.println();

        System.out.println("-----字符串列表-----");

        List<String> strList = new ArrayList<>();
        strList.add("Java编程思想");
        strList.add("易学Python");
        strList.add("IPSec VPN");

        System.out.println("字符串列表转JSON数组: " + GsonUtil.stringsToJSONArray(strList));
        System.out.println("字符串列表转JSON文本: " + GsonUtil.toText(strList));
        System.out.println();

        String stringArrayText = GsonUtil.toText(strList);
        System.out.println("JSON文本转JSON数组: " + GsonUtil.toJSONArray(stringArrayText));
        System.out.println("JSON文本转字符串列表: " + GsonUtil.toStringList(stringArrayText));
        System.out.println();

        JsonArray stringArray = GsonUtil.toJSONArray(stringArrayText);
        System.out.println("JSON数组转JSON文本: " + GsonUtil.toText(stringArray));
        System.out.println("JSON数组转字符串列表: " + GsonUtil.toStringList(stringArray));
        System.out.println();

        System.out.println("-----数值型列表-----");

        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(99);
        intList.add(-200);

        System.out.println("数值型列表转JSON数组: " + GsonUtil.numbersToJSONArray(intList));
        System.out.println("数值型列表转JSON文本: " + GsonUtil.toText(intList));
        System.out.println();

        String intArrayText = GsonUtil.toText(intList);
        System.out.println("JSON文本转JSON数组: " + GsonUtil.toJSONArray(intArrayText));
        System.out.println("JSON文本转数值型列表: " + GsonUtil.toNumberList(intArrayText));
        System.out.println();

        JsonArray intArray = GsonUtil.toJSONArray(intArrayText);
        System.out.println("JSON数组转JSON文本: " + GsonUtil.toText(intArray));
        System.out.println("JSON数组转数值型列表: " + GsonUtil.toNumberList(intArray));
        System.out.println();

        System.out.println("-----布尔型列表-----");

        List<Boolean> bList = new ArrayList<>();
        bList.add(false);
        bList.add(true);
        bList.add(false);

        System.out.println("布尔型列表转JSON数组: " + GsonUtil.booleansToJSONArray(bList));
        System.out.println("布尔型列表转JSON文本: " + GsonUtil.toText(bList));
        System.out.println();

        String bArrayText = GsonUtil.toText(bList);
        System.out.println("JSON文本转JSON数组: " + GsonUtil.toJSONArray(bArrayText));
        System.out.println("JSON文本转布尔型列表: " + GsonUtil.toBooleanList(bArrayText));
        System.out.println();

        JsonArray bArray = GsonUtil.toJSONArray(bArrayText);
        System.out.println("JSON数组转JSON文本: " + GsonUtil.toText(bArray));
        System.out.println("JSON数组转布尔型列表: " + GsonUtil.toBooleanList(bArray));
        System.out.println();

        /*
         * ---------- 其它功能测试 ----------
         */
        System.out.println("-----其它功能测试-----");

        System.out.println(GsonUtil.syntaxCheck("{name:1}"));
        System.out.println(GsonUtil.syntaxCheck(null));
        System.out.println(GsonUtil.syntaxCheck(""));
        System.out.println(GsonUtil.syntaxCheck("{abc}"));
    }
}
