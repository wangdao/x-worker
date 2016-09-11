package me.emou.xworker.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author wangdao
 */
public interface GeneratorService {

    String generateJava(String tableName, List<String> columnNames, String packageName) throws IOException;


    String generateMapper(String tableName, List<String> columnNames, String packageName) throws IOException;

    String generateSQL(String tableName, List<String> columnNames, String type);
}
