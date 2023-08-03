package cc.geekadmin.utils;

/**
 * @Author Admin
 * @create 2023/1/1 0:21
 */
public class StringUtils {

    /**
     * 首字母转大写
     * @param str 字符串
     * @return String
     */
    public static String Uppercase(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * 下划线转大写
     * @param para  字符串
     * @return String
     */
    public static String UnderlineToHump(String para) {
        if (StringUtils.isEmpty(para)) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    private static boolean isEmpty(String para) {
        return "".equals(para);
    }

}
