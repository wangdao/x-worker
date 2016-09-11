package me.emou.xworker.generator;

import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.*;

/**
 * @author wangdao
 */
public class SQLGenerator implements Generator<String> {

    private String type;

    protected JavaClassGenerator javaClassGenerator;

    protected VelocityEngine velocityEngine;

    public SQLGenerator(String type) {
        this.type = type;

        this.javaClassGenerator = new JavaClassGenerator();

        velocityEngine = new VelocityEngine();

        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        try {
            velocityEngine.init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("VelocityEngine can not initialize.");
            System.exit(0);
        }
    }

    @Override
    public String process(Table table) {
        String tpl = "tpl/select.vm";

        if ("select".equals(type)) {
            tpl = "tpl/select.vm";
        }

        if ("update".equals(type)) {
            tpl = "tpl/update.vm";
        }

        if ("insert".equals(type)) {
            tpl = "tpl/insert.vm";
        }

        try {
            JavaClass javaClass = javaClassGenerator.process(table);

            Template template = velocityEngine.getTemplate(tpl, "UTF-8");

            VelocityContext ctx = new VelocityContext();
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

            StringWriter stringWriter = new StringWriter();

            template.merge(ctx, stringWriter);

            return stringWriter.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<String> process(Collection<Table> tables) {
        return null;
    }
}
