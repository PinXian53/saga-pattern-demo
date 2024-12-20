package com.pino.webbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WebBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBffApplication.class, args);
    }

}
