package me.emou.xworker.pipeline;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.IOException;

/**
 * @author wangdao
 */
public interface Pipeline {

    void out(Template template, VelocityContext ctx) throws IOException;
}
