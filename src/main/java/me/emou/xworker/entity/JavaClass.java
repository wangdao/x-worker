package me.emou.xworker.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdao
 */
public class JavaClass {

    private String packageName;

    private String name;

    private String comment;

    private List<Field> fields;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void addField(Field field) {
        if (null == this.fields) {
            fields = new ArrayList<>();
        }

        this.fields.add(field);
    }
}
