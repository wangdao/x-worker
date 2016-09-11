package me.emou.xworker.generator.velocity;

import me.emou.xworker.entity.Field;
import me.emou.xworker.entity.JavaClass;
import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.BusinessException;
import me.emou.xworker.generator.JavaClassGenerator;
import me.emou.xworker.pipeline.Pipeline;
import me.emou.xworker.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangdao
 */
public class JavaClassFileGenerator extends VelocityGenerator {


    public JavaClassFileGenerator(String packageName) {
        super(packageName);
    }

    @Override
    public File process(Table table) {
        JavaClass javaClass = javaClassGenerator.process(table);
        javaClass.setPackageName(packageName);

        String tpl = "tpl/java.vm";

        try {
            Template template = velocityEngine.getTemplate(tpl, "UTF-8");

            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

            path = path.substring(1) + "tmp";

            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //File file = File.createTempFile(javaClass.getName(), ".java");

            File file = new File(path + File.separator + javaClass.getName() + ".java");
            if (file.exists()) {
                file.delete();
            }

            if (file.createNewFile()) {
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

                VelocityContext ctx = new VelocityContext();
                ctx.put("javaClass", javaClass);
                ctx.put("StringUtil", StringUtil.class);
                ctx.put("imports", pickJavaType(javaClass.getFields()));
//                ctx.put("StringUtil", StringUtil);

                template.merge(ctx, out);

                out.close();

                return file;
            }

            return null;
        }  catch (Exception e) {
            e.printStackTrace();

            throw new BusinessException(e);
        }

    }

    @Override
    public List<File> process(Collection<Table> tables) {
        return null;
    }

    private Set<String> pickJavaType(List<Field> fields) {
        Set<String> javaTypes = new HashSet<>();

        for (int i = 0; i < fields.size(); i++) {
            javaTypes.add(fields.get(i).getJavaTypeFullName());
        }

        return javaTypes;
    }
}
