package me.emou.xworker.generator;

import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.Field;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.util.DataTypeUtil;
import me.emou.xworker.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangdao
 */
public class JavaClassGenerator implements Generator<JavaClass> {

    private String packageName;

    public JavaClassGenerator() {
    }

    public JavaClassGenerator(String packageName) {
        this.packageName = packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public JavaClass process(Table table) {
        JavaClass javaClass = new JavaClass();

        String javaClassName = StringUtil.underline2Camel(table.getName());

        javaClass.setPackageName(packageName);
        javaClass.setName(StringUtil.capitalizeFirstLetter(javaClassName));

        List<Column> columns = table.getColumns();

        for (Column column : columns) {
            String fieldName = StringUtil.underline2Camel(column.getName());

            Field field = new Field();
            field.setName(fieldName);
            field.setComment(column.getComment());
            field.setJavaTypeFullName(DataTypeUtil.javaType(column.getDataType()));

            javaClass.addField(field);
        }

        return javaClass;
    }

    @Override
    public List<JavaClass> process(Collection<Table> tables) {
        List<JavaClass> javaClasses = new ArrayList<>();

        Iterator<Table> it = tables.iterator();

        while (it.hasNext()) {
            Table  t = it.next();

            javaClasses.add(process(t));
        }

        return javaClasses;
    }
}
