package me.emou.xworker.util;

/**
 * @author wangdao
 */
public abstract class DataTypeUtil {

    public static String javaType(String original) {
        String lower = original.toLowerCase();

        if (lower.startsWith("number")) {
            return "java.math.BigDecimal";
        }

        if (lower.startsWith("timestamp")) {
            return "java.security.Timestamp";
        }

        return "java.lang.String";
    }
}
