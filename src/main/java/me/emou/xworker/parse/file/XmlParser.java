package me.emou.xworker.parse.file;

import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.ParseException;
import me.emou.xworker.parse.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdao
 */
public class XmlParser implements Parser {

    private InputStream in;

    public XmlParser(File file) throws FileNotFoundException {
        in = new FileInputStream(file);
    }

    public XmlParser(InputStream in) {
        this.in = in;
    }

    @Override
    public List<Table> parse() {
        List<Table> tables = new ArrayList<Table>();

        Document document = null;
        try {
            document = Jsoup.parse(in, "UTF-8", "http://emou.me");
        } catch (IOException e) {
            throw new ParseException(e);
        }

        Elements elements = document.getElementsByTag("o:Table");

        for (Element ele : elements) {
            Table table = parseTable(ele);

            if (table != null) {
                tables.add(table);
            }
        }

        return tables;
    }

    private Table parseTable(Element element) {
        Table table = null;

        if (support(element)) {
            table = doParse(element);

            String tableName = element.getElementsByTag("a:name").first().text();
            String tableCode = element.getElementsByTag("a:code").first().text();

            table.setName(tableCode);
            table.setComment(tableName);
        }


        return table;
    }

    private boolean support(Element element) {

        return element.getElementsByTag("c:columns").size() > 0;
    }

    private Table doParse(Element element) {
        Elements elements = element.getElementsByTag("c:columns").first().children();

        Table table = new Table();

        for (int i = 0; i < elements.size(); i++) {
            Element el = elements.get(i);

            String name = el.getElementsByTag("a:name").first().text();
            String code = el.getElementsByTag("a:code").first().text();
            String dataType = el.getElementsByTag("a:datatype").first().text();

            Column column = new Column();
            column.setComment(name);
            column.setName(code);
            column.setDataType(dataType);

            table.addColumn(column);
        }

        return table;
    }
}
