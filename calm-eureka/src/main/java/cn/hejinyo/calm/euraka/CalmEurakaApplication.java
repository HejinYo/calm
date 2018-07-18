package cn.hejinyo.calm.euraka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CalmEurakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalmEurakaApplication.class, args);
    }
}
