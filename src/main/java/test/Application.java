package test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 260180 on 2017/10/31.
 */
@MapperScan("test.Dao.Po")
@SpringBootApplication
@EnableSwagger2
//@EnableConfigurationProperties(JwtInfo.class)
public class Application {

    public static void main(String[] args)throws Exception{
        SpringApplication.run(Application.class, args);
    }


}
