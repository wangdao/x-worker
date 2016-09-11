package me.emou.xworker.util;

/**
 * @author wangdao
 */
public abstract class StringUtil {

    public static String underline2Camel(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String lower = str.toLowerCase();

        String[] tmp = lower.split("_");

        StringBuilder sb = new StringBuilder();
        sb.append(tmp[0]);

        for (int i = 1; i < tmp.length; i++) {
            sb.append(capitalizeFirstLetter(tmp[i]));
        }

        return sb.toString();
    }

    public static String capitalizeFirstLetter(String str) {
        char[] chars = str.toCharArray();

        if ((chars[0] >= 97 && chars[0] <= 122)) {
            chars[0] -= 32;
        }

        return String.valueOf(chars);
    }

}
