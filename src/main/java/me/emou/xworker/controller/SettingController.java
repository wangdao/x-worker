package me.emou.xworker.controller;

import me.emou.xworker.db.Db;
import me.emou.xworker.service.SettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author wangdao
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    @Resource
    private SettingService settingService;


    @RequestMapping("database/tester")
    public boolean testDatabase(String type, String ip, String port, String databaseName, String username, String pwd) {
        settingService.validateDatabaseConfig(type, ip, port, databaseName, username, pwd);

        return true;
    }


    @RequestMapping(value = "/uploader", method = RequestMethod.POST)
    public String uploadPdm(MultipartFile file) throws IOException {

        Db.pdmInputStream = file.getInputStream();

        return "dddd";
    }

}
