package com.foodMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com", "org.n3r.idworker"})
//@EnableTransactionManagement
//@EnableScheduling       // 开启定时任务
public class FoodMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodMallApplication.class,args);
    }
}
