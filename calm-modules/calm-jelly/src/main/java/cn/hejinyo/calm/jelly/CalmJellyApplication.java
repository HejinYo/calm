package cn.hejinyo.calm.jelly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan(basePackages = {"cn.hejinyo.calm.jelly", "cn.hejinyo.calm.common"})
public class CalmJellyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalmJellyApplication.class, args);
    }
}
