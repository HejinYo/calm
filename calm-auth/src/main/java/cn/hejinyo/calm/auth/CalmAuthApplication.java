package cn.hejinyo.calm.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan(basePackages = {"cn.hejinyo.calm.auth", "cn.hejinyo.calm.common"})
public class CalmAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalmAuthApplication.class, args);
    }
}
