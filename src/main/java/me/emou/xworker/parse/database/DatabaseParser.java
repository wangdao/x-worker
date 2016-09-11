package me.emou.xworker.parse.database;

import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.ParseException;
import me.emou.xworker.parse.Parser;
import me.emou.xworker.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdao
 */
public class DatabaseParser implements Parser {

    private Connection connection;

    public DatabaseParser(Connection conn) {
        this.connection = conn;
    }

    @Override
    public List<Table> parse()  {

        try {
            return databaseTables();
        } catch (SQLException e) {
            throw new ParseException(e);
        }
    }

    private List<Table> databaseTables() throws SQLException {
        List<Table> tables = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();

        String[] types = {"TABLE"};
        ResultSet rs = metaData.getTables(null, null, "%", types);

        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");  //表名
            String remark = rs.getString("REMARKS");

            Table table = new Table();
            table.setName(tableName);
            table.setComment(remark);

            tables.add(table);
        }

        fillTable(tables, connection);

        DatabaseUtil.close(connection);

        return tables;
    }

    private void fillTable(List<Table> tables, Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();


        for (Table table : tables) {
            String tableName = table.getName();
            ResultSet rs = metaData.getColumns(null, null, tableName, null);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataTypeName = rs.getString("TYPE_NAME");
                String remarks = rs.getString("REMARKS");

                Column column = new Column(columnName, remarks, dataTypeName);

                table.addColumn(column);
            }

        }
    }
}
