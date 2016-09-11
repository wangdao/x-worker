package me.emou.xworker.generator;

import me.emou.xworker.entity.Table;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author wangdao
 */
public class FileGenerator implements Generator<File> {

    @Override
    public File process(Table table) {
        return null;
    }

    @Override
    public List<File> process(Collection<Table> tables) {
        return null;
    }
}
