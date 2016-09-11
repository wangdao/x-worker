package me.emou.xworker.service.impl;

import me.emou.xworker.db.Db;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.BusinessException;
import me.emou.xworker.generator.Generator;
import me.emou.xworker.generator.JavaClassGenerator;
import me.emou.xworker.parse.Parser;
import me.emou.xworker.parse.database.DatabaseParser;
import me.emou.xworker.parse.database.EDatabaseType;
import me.emou.xworker.parse.file.XmlParser;
import me.emou.xworker.service.DataSourceService;
import me.emou.xworker.util.DatabaseUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangdao
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Override
    public List<Table> allTables() {
        List<Table> r = new ArrayList<>();

        Map<String, Table> tables = Db.getTables();

        for (Map.Entry<String, Table> entry : tables.entrySet()) {
            r.add(entry.getValue());
        }

        return r;
    }

    @Override
    public void syncByDatabase(String type, String ip, String port, String databaseName, String username, String pwd) {
        String url = "";
        EDatabaseType databaseType = null;

        if ("mysql".equals(type)) {
            url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;
            databaseType = EDatabaseType.MYSQL;
        }

        if ("oracle".equals(type)) {
            url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + databaseName;
            databaseType = EDatabaseType.ORACLE;
        }

        try {
            Connection conn = DatabaseUtil.getConnection(url, username, pwd, databaseType);

            Parser parser = new DatabaseParser(conn);
            List<Table> tables = parser.parse();

            Db.addTable(tables);

            syncJavaClass(tables);


        } catch (Exception e) {
            throw new BusinessException("数据库连接失败。", e);
        }
    }

    @Override
    public void syncByPdm() {

        InputStream in = Db.pdmInputStream;

        if (null != in) {
            Parser parser = new XmlParser(in);
            List<Table> tables = parser.parse();

            Db.addTable(tables);

            try {
                syncJavaClass(tables);
            } catch (Exception e) {
                throw new BusinessException(e);
            }
        }

    }

    private void syncJavaClass(List<Table> tables) throws Exception {
        Generator<JavaClass> javaClassGenerator = new JavaClassGenerator();
        List<JavaClass> javaClasses = javaClassGenerator.process(tables);

        Db.addJavaClass(javaClasses);
    }
}
