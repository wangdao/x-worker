package me.emou.xworker.db;

import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.util.StringUtil;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wangdao
 */
public class Db {

    public static InputStream pdmInputStream = null;

    private static Map<String, Table> tables = new HashMap<>();

    private static Map<String, JavaClass> javaClasses = new HashMap<>();


    public static Map<String, Table> getTables() {
        return tables;
    }

    public static Map<String, JavaClass> getJavaClasses() {
        return javaClasses;
    }

    public static Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public static JavaClass getJavaClass(String className) {
        return javaClasses.get(className);
    }

    public static JavaClass getJavaClassByTableName(String tableName) {
        String javaClassName = StringUtil.capitalizeFirstLetter(StringUtil.underline2Camel(tableName));

        return getJavaClass(javaClassName);
    }

    public static void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public static void addTable(Collection<Table> tables) {
        Iterator<Table> it = tables.iterator();

        while (it.hasNext()) {
            Table table = it.next();

            addTable(table);
        }
    }

    public static void addJavaClass(JavaClass javaClass) {
        String className = javaClass.getName();
        javaClasses.put(className, javaClass);
    }

    public static void addJavaClass(Collection<JavaClass> javaClasses) {
        Iterator<JavaClass> it = javaClasses.iterator();
        while (it.hasNext()) {
            addJavaClass(it.next());
        }
    }

}
