package me.emou.xworker.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import me.emou.xworker.exception.ParseException;
import me.emou.xworker.parse.database.EDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdao
 */
public abstract class DatabaseUtil {

    public static Connection getConnection(String url,
                                           String userName, String password, EDatabaseType databaseType) throws Exception {

        String driverClassName = "";
        if (EDatabaseType.MYSQL.equals(databaseType)) {
            driverClassName = "com.mysql.jdbc.Driver";
        }

        if (EDatabaseType.ORACLE.equals(databaseType)) {
            driverClassName = "oracle.jdbc.driver.OracleDriver";
        }


        Map<String, String> config = new HashMap<>();
        config.put("url", url);
        config.put("driverClassName", driverClassName);
        config.put("username", userName);
        config.put("password", password);


        DataSource dataSource = DruidDataSourceFactory.createDataSource(config);

        return dataSource.getConnection();

    }

    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
}
