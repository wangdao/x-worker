package me.emou.xworker.parse;

import me.emou.xworker.entity.Table;

import java.io.IOException;
import java.util.List;

/**
 * @author wangdao
 */
public interface Parser {

    List<Table> parse();
}
