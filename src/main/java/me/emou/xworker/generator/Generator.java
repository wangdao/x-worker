package me.emou.xworker.generator;

import me.emou.xworker.entity.Table;

import java.util.Collection;
import java.util.List;

/**
 * @author wangdao
 */
public interface Generator<T> {

    T process(Table table);

    List<T> process(Collection<Table> tables);
}
