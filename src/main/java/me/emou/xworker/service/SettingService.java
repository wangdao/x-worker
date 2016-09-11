package me.emou.xworker.service;

/**
 * @author wangdao
 */
public interface SettingService  {

    void validateDatabaseConfig(String type, String ip, String port, String databaseName, String username, String pwd);
}
