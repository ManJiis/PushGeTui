package top.b0x0.getui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.b0x0.gexin.mapper")
public class GetuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetuiApplication.class, args);
    }

}
