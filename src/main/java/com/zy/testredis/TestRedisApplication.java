package com.zy.testredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestRedisApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(TestRedisApplication.class, args);
        MyConfig bean = ctx.getBean(MyConfig.class);
        bean.test();
    }

}
