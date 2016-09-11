package me.emou.xworker.controller;

import com.alibaba.fastjson.JSON;
import me.emou.xworker.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author wangdao
 */
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @RequestMapping(value = "java", method = RequestMethod.POST)
    public String java(String tableName,  String columns, String packageName) throws IOException {

        if (null == columns) {
            columns = "[]";
        }

        return generatorService.generateJava(tableName , JSON.parseArray(columns, String.class), packageName);

    }

    @RequestMapping(value = "mapper", method = RequestMethod.POST)
    public String mapper(String tableName, String columns, String packageName) throws IOException {
        if (null == columns) {
            columns = "[]";
        }
        return generatorService.generateMapper(tableName , JSON.parseArray(columns, String.class), packageName);
    }

    @RequestMapping(value = "sql", method = RequestMethod.POST)
    public String sql(String tableName, String columns, String type) {
        if (null == columns) {
            columns = "[]";
        }

        return generatorService.generateSQL(tableName, JSON.parseArray(columns, String.class), type);
    }
}
