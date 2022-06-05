package com.ruoyi.yjy;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * yjy模块
 *
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class YjySysApplication {
    public static void main(String[] args)
    {


        SpringApplication.run(YjySysApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  杨佳仪的系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. |\n" +
                "| |  ____  ____  | || |     _____    | || |  ____  ____  | |\n" +
                "| | |_  _||_  _| | || |    |_   _|   | || | |_  _||_  _| | |\n" +
                "| |   \\ \\  / /   | || |      | |     | || |   \\ \\  / /   | |\n" +
                "| |    \\ \\/ /    | || |   _  | |     | || |    \\ \\/ /    | |\n" +
                "| |    _|  |_    | || |  | |_' |     | || |    _|  |_    | |\n" +
                "| |   |______|   | || |  `.___.'     | || |   |______|   | |\n" +
                "| |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'");
    }

}
