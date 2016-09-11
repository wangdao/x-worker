package me.emou.xworker.generator;

import com.alibaba.fastjson.JSON;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.parse.Parser;
import me.emou.xworker.parse.database.DatabaseParser;
import me.emou.xworker.parse.database.EDatabaseType;
import me.emou.xworker.util.DatabaseUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangdao
 */
public class JavaClassGeneratorTest {

    @Test
    public void process() throws Exception {
        Connection connection = DatabaseUtil.getConnection("jdbc:mysql://localhost:3306/lr_loan",
                "root", "root", EDatabaseType.MYSQL);

        Parser parser = new DatabaseParser(connection);
        List<Table> tables = parser.parse();

        Generator<JavaClass> generator = new JavaClassGenerator();

        List<JavaClass> javaClasses = generator.process(tables);

        System.out.println(JSON.toJSONString(javaClasses));

    }

}