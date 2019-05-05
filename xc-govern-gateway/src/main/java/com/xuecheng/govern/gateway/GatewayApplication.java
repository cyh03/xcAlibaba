package com.xuecheng.govern.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * @author Administrator
 * @version 1.0
 * @create 2019-04-23 12:03
 **/
@SpringBootApplication
@EnableZuulProxy//此工程是一个zuul网关
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 动态路由
     * 第一点导入包
     * 第二点配置该代码
     * @return
     */
 //   @ConfigurationProperties("zuul")
  //  @RefreshScope
  //  public ZuulProperties  zuulProperties(){
    //    return  new ZuulProperties();


}
