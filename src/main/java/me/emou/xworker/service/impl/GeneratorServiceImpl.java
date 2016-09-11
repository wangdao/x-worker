package me.emou.xworker.service.impl;

import me.emou.xworker.db.Db;
import me.emou.xworker.entity.Column;
import me.emou.xworker.entity.Table;
import me.emou.xworker.exception.BusinessException;
import me.emou.xworker.generator.Generator;
import me.emou.xworker.generator.SQLGenerator;
import me.emou.xworker.generator.velocity.JavaClassFileGenerator;
import me.emou.xworker.generator.velocity.MapperXmlFileGenerator;
import me.emou.xworker.service.GeneratorService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdao
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Override
    public String generateJava(String tableName, List<String> columnNames, String packageName) throws IOException {
        Table table = getTable(tableName, columnNames);

        Generator<File> fileGenerator = new JavaClassFileGenerator(packageName);

        File file = fileGenerator.process(table);

        if (file != null) {
            return file.getName();
        }

        throw new BusinessException("文件创建失败");

    }

    @Override
    public String generateMapper(String tableName, List<String> columnNames, String packageName) throws IOException {
        Table table = getTable(tableName, columnNames);

        Generator<File> fileGenerator = new MapperXmlFileGenerator(packageName);

        File file = fileGenerator.process(table);

        if(file != null) {
            return file.getName();
        }

        return null;
    }

    @Override
    public String generateSQL(String tableName, List<String> columnNames, String type) {
        Table table = getTable(tableName, columnNames);

        Generator<String> generator = new SQLGenerator(type);


        return generator.process(table);
    }

    private Table getTable(String tableName, List<String> columnNames) {
        Table table = Db.getTable(tableName);

        List<Column> columns = new ArrayList<>();

        List<Column> allColumns = table.getColumns();

        if (null != columnNames && columnNames.size() > 0) {
            for (String columnName :
                    columnNames) {
                for (Column column :
                        allColumns) {
                    if (columnName.equals(column.getName())) {
                        columns.add(column);

                        break;
                    }
                }
            }

            table.setColumns(columns);
        }

        return table;
    }
}
