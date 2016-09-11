package me.emou.xworker.service.impl;

import me.emou.xworker.exception.BusinessException;
import me.emou.xworker.parse.database.EDatabaseType;
import me.emou.xworker.service.SettingService;
import me.emou.xworker.util.DatabaseUtil;
import org.springframework.stereotype.Service;

/**
 * @author wangdao
 */
@Service
public class SettingServiceImpl implements SettingService {

    public SettingServiceImpl() {
        System.out.println("********************");
    }

    @Override
    public void validateDatabaseConfig(String type, String ip, String port, String databaseName,
                                       String username, String pwd) {
        String url = "";
        EDatabaseType databaseType = null;

        if ("mysql".equals(type)) {
            url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;
            databaseType = EDatabaseType.MYSQL;
        }

        if ("oracle".equals(type)) {
            url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + databaseName;
            databaseType = EDatabaseType.ORACLE;
        }

        try {
            DatabaseUtil.getConnection(url, username, pwd, databaseType);
        } catch (Exception e) {
            throw new BusinessException("测试失败！", e);
        }

    }
}
