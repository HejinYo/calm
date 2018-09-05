package cn.hejinyo.calm.jelly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"cn.hejinyo.calm.jelly", "cn.hejinyo.calm.common"})
public class CalmJellyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalmJellyApplication.class, args);
    }
}
