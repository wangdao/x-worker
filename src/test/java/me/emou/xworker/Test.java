package me.emou.xworker;

import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @author wangdao
 */
public class Test {

    @org.junit.Test
    public void method() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        path = path.substring(1) + "tmp";

        System.out.println(path);

        File folder = new File(path);

        folder.mkdir();
    }
}
