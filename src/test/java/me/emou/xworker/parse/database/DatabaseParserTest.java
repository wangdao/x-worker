package me.emou.xworker.parse.database;

import com.alibaba.fastjson.JSON;
import me.emou.xworker.entity.Table;
import me.emou.xworker.parse.Parser;
import me.emou.xworker.util.DatabaseUtil;
import org.jsoup.helper.DataUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangdao
 */
public class DatabaseParserTest {
    @Test
    public void mySQLParse() throws Exception {
        Connection connection = DatabaseUtil.getConnection("jdbc:mysql://localhost:3306/lr_loan",
                "root", "root", EDatabaseType.MYSQL);

        Parser parser = new DatabaseParser(connection);
        List<Table> tables = parser.parse();

        System.out.println(JSON.toJSONString(tables));

    }

    @Test
    public void oracleParse() throws Exception {
        Connection connection = DatabaseUtil.getConnection("jdbc:oracle:thin:@182.92.8.135:1521:orcl",
                " zjt_bl1", "1", EDatabaseType.ORACLE);

        Parser parser = new DatabaseParser(connection);
        List<Table> tables = parser.parse();

        System.out.println(JSON.toJSONString(tables));
    }

}