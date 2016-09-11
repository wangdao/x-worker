package me.emou.xworker.entity;

/**
 * @author wangdao
 */
public class Field {

    private String name;

    private String comment;

    private String javaType;

    private String javaTypeFullName;

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

    public String getJavaType() {
        return javaType;
    }

    public String getJavaTypeFullName() {
        return javaTypeFullName;
    }

    public void setJavaTypeFullName(String javaTypeFullName) {
        this.javaTypeFullName = javaTypeFullName;

        int lastDotIndex = javaTypeFullName.lastIndexOf(".");

        this.javaType = javaTypeFullName.substring(++lastDotIndex);
    }


}
