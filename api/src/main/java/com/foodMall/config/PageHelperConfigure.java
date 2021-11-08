package com.foodMall.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * PageHelper 分页配置
 *
 * @author zx
 * @date 2021年07月29日
 */
@Configuration
public class PageHelperConfigure {

    /**
     * 配置插件
     *
     * @return bean
     */
    @Bean(name = "plugins")
    public Interceptor[] plugins() {

        //org.apache.ibatis.plugin.Interceptor
        Interceptor interceptor = new PageInterceptor();

        //java.util.Properties
        Properties properties = new Properties();

        //是否将参数offset作为pageNum
        properties.setProperty("offsetAsPageNum", "true");

        // 数据库类型
        properties.setProperty("helperDialect", "mysql");

        // 是否返回行数，相当于MySQL的count(*)
        properties.setProperty("rowBoundsWithCount", "true");

        // 是否分页合理化：即翻页小于0时，显示第一页数据，翻页数较大查不到数据时，显示最后一页的数据，默认即为false，本处显示写出，以供参考。
        properties.setProperty("reasonable", "false");

        interceptor.setProperties(properties);

        Interceptor[] plugins = new Interceptor[1];

        plugins[0] = interceptor;

        return plugins;
    }

}