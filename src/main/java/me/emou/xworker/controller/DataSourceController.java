package me.emou.xworker.controller;

import me.emou.xworker.entity.Table;
import me.emou.xworker.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangdao
 */
@RestController
@RequestMapping("dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Table> allTables() {
        return dataSourceService.allTables();
    }

    @RequestMapping(value = "sync/database", method = RequestMethod.POST)
    public boolean syncByDatabase(String type, String ip, String port, String databaseName, String username, String pwd) {
        dataSourceService.syncByDatabase(type, ip, port, databaseName, username, pwd);

        return true;
    }

    @RequestMapping(value = "sync/pdm", method = RequestMethod.POST)
    public boolean syncByPdm() {
        dataSourceService.syncByPdm();
        return true;
    }
}
