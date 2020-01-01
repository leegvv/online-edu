package net.arver.onlineedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.arver.onlineedu.mapper")
public class OnlineEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEduApplication.class, args);
    }

}
