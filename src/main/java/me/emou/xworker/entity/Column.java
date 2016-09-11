package me.emou.xworker.entity;

/**
 * @author wangdao
 */
public class Column {

    private String name;

    private String comment;

    private String dataType;

    public Column() {
    }

    public Column(String name, String comment, String dataType) {
        this.name = name;
        this.comment = comment;
        this.dataType = dataType;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
