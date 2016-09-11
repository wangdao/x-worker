package me.emou.xworker.generator.velocity;

import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * @author wangdao
 */
public class MapperXmlFileGenerator extends VelocityGenerator {


    public MapperXmlFileGenerator(String packageName) {
        super(packageName);
    }

    @Override
    public File process(Table table) {
        JavaClass javaClass = javaClassGenerator.process(table);

        String tpl = "tpl/mapper.vm";


        try {
            Template template = velocityEngine.getTemplate(tpl, "UTF-8");
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

            path = path.substring(1) + "tmp";

            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(path + File.separator + javaClass.getName() + "Mapper.xml");
            if (file.exists()) {
                file.delete();
            }

            if (file.createNewFile()) {
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

                VelocityContext ctx = new VelocityContext();
                ctx.put("className", javaClass.getName());
                ctx.put("tableName", table.getName());

                List<Map<String, String>> fields = new ArrayList<>();

                List<Column> columns = table.getColumns();
                for (Column column :
                        columns) {

                    String javaClassField = StringUtil.underline2Camel(column.getName());

                    Map<String, String> field = new HashMap<>();
                    field.put("name", javaClassField);
                    field.put("column", column.getName());

                    fields.add(field);
                }

                ctx.put("fields", fields);


                template.merge(ctx, out);

                out.close();

                return file;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<File> process(Collection<Table> tables) {
        return null;
    }
}
