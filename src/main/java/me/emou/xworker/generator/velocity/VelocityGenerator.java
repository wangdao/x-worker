package me.emou.xworker.generator.velocity;

import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.BusinessException;
import me.emou.xworker.generator.Generator;
import me.emou.xworker.generator.JavaClassGenerator;
import me.emou.xworker.pipeline.Pipeline;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;


/**
 * @author wangdao
 */
public abstract class VelocityGenerator implements Generator<File> {

    protected JavaClassGenerator javaClassGenerator;

    protected String packageName;

    protected VelocityEngine velocityEngine;


    public VelocityGenerator(String packageName ) {
        this.packageName = packageName;
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

    public abstract File process(Table table);

    public abstract List<File> process(Collection<Table> tables);


}
