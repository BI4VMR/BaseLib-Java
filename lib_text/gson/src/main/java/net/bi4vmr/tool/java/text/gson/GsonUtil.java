package net.bi4vmr.tool.java.text.gson;

import com.google.gson.*;
import net.bi4vmr.tool.java.text.base.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * GSON工具。
 * <p>
 * 基于GSON库，提供JSON对象、JSON文本和Java实例的相互转化功能。
 *
 * @author BI4VMR@outlook.com
 */
public class GsonUtil {

    /*
     * ---------- JSONObject、Java对象、JSON文本的相互转换。 ----------
     */

    /**
     * 将JSON对象转为JSON文本。
     *
     * @param json 待转换的JSON对象。
     * @return JSON文本。传入参数为空时，将返回内容为空的字符串。
     */
    public static String toText(JsonObject json) {
        return (json == null) ? "" : json.toString();
    }

    /**
     * 将Java对象转为JSON文本。
     *
     * @param o 待转换的Java对象，可以是List类型。
     * @return JSON文本。传入对象为空时，将得到Null。传入对象为String时，将得到原始文本内容，而不是String对象的JSON格式文本。
     */
    public static String toText(Object o) {
        return new Gson().toJson(o);
    }

    /**
     * 将文本转为JSON对象。
     *
     * @param text 待转换的JSON文本。
     * @return JSON对象，文本格式错误时将返回空值。
     */
    public static JsonObject toJSONObject(String text) {
        JsonElement element = parseFromText(text);
        if (element == null || !element.isJsonObject()) {
            return null;
        } else {
            return element.getAsJsonObject();
        }
    }

    /**
     * 将Java对象转为JSON对象。
     *
     * @param o 待转换的Java对象。
     * @return JSON对象。
     */
    public static JsonObject toJSONObject(Object o) {
        return toJSONObject(toText(o));
    }

    /**
     * 将JSON文本转换为Java对象。
     *
     * @param text JSON字符串。
     * @param cls  实体类Class。
     * @param <T>  Java对象的类型。
     * @return Java对象。
     */
    public static <T> T toInstance(String text, Class<T> cls) {
        return new Gson().fromJson(text, cls);
    }

    /**
     * 将JSON对象转换为Java对象。
     *
     * @param json JSON对象。
     * @param cls  实体类Class。
     * @param <T>  Java对象的类型。
     * @return Java对象。
     */
    public static <T> T toInstance(JsonObject json, Class<T> cls) {
        return new Gson().fromJson(json, cls);
    }

    /*
     * ---------- JSONArray、Java集合、JSON文本的相互转换。 ----------
     */

    /**
     * 将JSON数组转为JSON文本。
     *
     * @param array 待转换的JSON数组。
     * @return JSON文本。传入参数为空时，将返回内容为空的字符串。
     */
    public static String toText(JsonArray array) {
        return (array == null) ? "" : array.toString();
    }

    /**
     * 将文本转为JSON数组。
     *
     * @param text 待转换的JSON文本。
     * @return JSON数组，文本格式错误时将返回空值。
     */
    public static JsonArray toJSONArray(String text) {
        JsonElement element = parseFromText(text);
        if (element == null || !element.isJsonArray()) {
            return null;
        } else {
            return element.getAsJsonArray();
        }
    }

    /**
     * 将实体类List转为JSON数组。
     *
     * @param list 待转换的List。
     * @return JSON数组。
     */
    public static JsonArray entitysToJSONArray(List<?> list) {
        JsonArray array = new JsonArray();
        // 向JSON数组中添加元素
        for (Object o : list) {
            array.add(toJSONObject(o));
        }
        return array;
    }

    /**
     * 将字符串List转为JSON数组。
     *
     * @param list 待转换的集合。
     * @return JSON数组。
     */
    public static JsonArray stringsToJSONArray(List<? extends CharSequence> list) {
        JsonArray array = new JsonArray();
        // 向JSON数组中添加元素
        for (CharSequence s : list) {
            array.add(s.toString());
        }
        return array;
    }

    /**
     * 将数值型List转为JSON数组。
     *
     * @param list 待转换的集合。
     * @return JSON数组。
     */
    public static JsonArray numbersToJSONArray(List<? extends Number> list) {
        JsonArray array = new JsonArray();
        // 向JSON数组中添加元素
        for (Number num : list) {
            array.add(num);
        }
        return array;
    }

    /**
     * 将布尔型List转为JSON数组。
     *
     * @param list 待转换的集合。
     * @return JSON数组。
     */
    public static JsonArray booleansToJSONArray(List<Boolean> list) {
        JsonArray array = new JsonArray();
        // 向JSON数组中添加元素
        for (Boolean b : list) {
            array.add(b);
        }
        return array;
    }

    /**
     * 将JSON数组转为实体类列表。
     * <p>
     * Java List中只能有一种类型，因此数组内的元素不能存在嵌套关系。
     *
     * @param array 待转换的JSON数组。
     * @return 实体类列表，如果解析错误则为空值。
     */
    public static <T> List<T> toEntityList(JsonArray array, Class<T> cls) {
        if (array == null) {
            return null;
        }

        List<T> list = new ArrayList<>();
        // 遍历数组，将其中的元素转换为对象。
        for (JsonElement element : array) {
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                try {
                    T e = toInstance(obj, cls);
                    list.add(e);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }

    /**
     * 将JSON数组转为字符串列表。
     * <p>
     * Java List中只能有一种类型，因此数组内的元素不能存在嵌套关系。
     *
     * @param array 待转换的JSON数组。
     * @return 字符串列表，如果解析错误则为空值。
     */
    public static List<String> toStringList(JsonArray array) {
        if (array == null) {
            return null;
        }

        List<String> list = new ArrayList<>();
        // 遍历数组，将其中的元素转换为字符串。
        for (JsonElement element : array) {
            try {
                String s = element.getAsString();
                list.add(s);
            } catch (Exception e) {
                e.printStackTrace();
                // 解析失败时返回空值
                return null;
            }
        }
        return list;
    }

    /**
     * 将JSON数组转为数值型列表。
     * <p>
     * Java List中只能有一种类型，因此数组内的元素不能存在嵌套关系。
     *
     * @param array 待转换的JSON数组。
     * @return 数值型列表，如果解析错误则为空值。
     */
    public static List<Number> toNumberList(JsonArray array) {
        if (array == null) {
            return null;
        }

        List<Number> list = new ArrayList<>();
        // 遍历数组，将其中的元素转换为字符串。
        for (JsonElement element : array) {
            try {
                Number s = element.getAsNumber();
                list.add(s);
            } catch (Exception e) {
                e.printStackTrace();
                // 解析失败时返回空值
                return null;
            }
        }
        return list;
    }

    /**
     * 将JSON数组转为布尔型列表。
     * <p>
     * Java List中只能有一种类型，因此数组内的元素不能存在嵌套关系。
     *
     * @param array 待转换的JSON数组。
     * @return 布尔型列表，如果解析错误则为空值。
     */
    public static List<Boolean> toBooleanList(JsonArray array) {
        if (array == null) {
            return null;
        }

        List<Boolean> list = new ArrayList<>();
        // 遍历数组，将其中的元素转换为布尔值。
        for (JsonElement element : array) {
            try {
                Boolean b = element.getAsBoolean();
                list.add(b);
            } catch (Exception e) {
                e.printStackTrace();
                // 解析失败时返回空值
                return null;
            }
        }
        return list;
    }

    /**
     * 将JSON文本转为实体类列表。
     *
     * @param text 待转换的JSON文本。
     * @param cls  List元素对应的实体类Class。
     * @param <T>  实体类的类型。
     * @return 实体类列表，如果解析错误则为空值。
     */
    public static <T> List<T> toEntityList(String text, Class<T> cls) {
        JsonArray array = toJSONArray(text);
        return toEntityList(array, cls);
    }

    /**
     * 将JSON文本转为字符串列表。
     *
     * @param text 待转换的JSON文本。
     * @return 字符串列表，如果解析错误则为空值。
     */
    public static List<String> toStringList(String text) {
        JsonArray array = toJSONArray(text);
        return toStringList(array);
    }

    /**
     * 将JSON文本转为数值型列表。
     *
     * @param text 待转换的JSON文本。
     * @return 数值型列表，如果解析错误则为空值。
     */
    public static List<Number> toNumberList(String text) {
        JsonArray array = toJSONArray(text);
        return toNumberList(array);
    }

    /**
     * 将JSON文本转为布尔型列表。
     *
     * @param text 待转换的JSON文本。
     * @return 布尔值列表，如果解析错误则为空值。
     */
    public static List<Boolean> toBooleanList(String text) {
        JsonArray array = toJSONArray(text);

        return toBooleanList(array);
    }

    /*
     * ---------- 其它功能 ----------
     */

    /**
     * 检查JSON文本是否符合语法规范。
     *
     * @param text 待测试的文本。
     * @return 语法正确时，返回"true"；语法错误时，返回"false"。
     */
    public static boolean syntaxCheck(String text) {
        return (parseFromText(text) != null);
    }

    /**
     * 将JSON文本转为JsonElement。
     *
     * @param text 待转换的文本。
     * @return 语法正确时，返回转换后的JsonElement实例；语法错误时，返回为Null。
     */
    private static JsonElement parseFromText(String text) {
        // 字符串为空时，无法通过语法检查。
        if (TextUtil.isEmpty(text)) {
            return null;
        }

        try {
            return JsonParser.parseString(text);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
