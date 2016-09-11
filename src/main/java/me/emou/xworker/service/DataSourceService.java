package me.emou.xworker.service;

import me.emou.xworker.entity.Table;

import java.util.List;

/**
 * @author wangdao
 */
public interface DataSourceService {

    List<Table> allTables();

    void syncByDatabase(String type, String ip, String port, String databaseName, String username, String pwd);

    void syncByPdm();
}
