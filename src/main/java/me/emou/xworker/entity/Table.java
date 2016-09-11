package me.emou.xworker.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdao
 */
public class Table {

    private String name;

    private String comment;

    private List<Column> columns;

    public Table() {
    }

    public Table(String name, String comment) {
        this.name = name;
        this.comment = comment;
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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column) {
        if (null == this.columns) {
            this.columns = new ArrayList<>();
        }

        this.columns.add(column);
    }
}
