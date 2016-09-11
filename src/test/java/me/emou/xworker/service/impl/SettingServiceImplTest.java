package me.emou.xworker.service.impl;

import me.emou.xworker.config.SpringConfig;
import me.emou.xworker.service.SettingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author wangdao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class SettingServiceImplTest {

    @Resource
    private SettingService settingService;

    @Test
    public void test() {
        assertNotNull(settingService);
    }

}