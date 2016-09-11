package me.emou.xworker.pipeline;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author wangdao
 */
public class ConsolePipeline implements Pipeline {

    private StringWriter sw = new StringWriter();

    public void out(Template template, VelocityContext ctx) throws IOException {
        template.merge(ctx, sw);

        System.out.println(sw.toString());
    }
}
