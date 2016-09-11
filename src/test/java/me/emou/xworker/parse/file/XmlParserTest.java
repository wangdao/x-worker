package me.emou.xworker.parse.file;

import com.alibaba.fastjson.JSON;
import me.emou.xworker.entity.Table;
import me.emou.xworker.parse.Parser;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangdao
 */
public class XmlParserTest {
    @Test
    public void parse() throws Exception {
        File file = new File("E:\\test.pdm");
        Parser parser = new XmlParser(file);

        List<Table> tables = parser.parse();

        System.out.println(JSON.toJSONString(tables));

    }

}