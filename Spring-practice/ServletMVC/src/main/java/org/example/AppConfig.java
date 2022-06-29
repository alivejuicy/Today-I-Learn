package org.example;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

//스프링 컨테이너
@Configuration
@ComponentScan
public class AppConfig {

}
//Model(서비스) 비즈니스 로직
@Service
public class HelloService{
    public String getName(){
        return "alivejuicy";
    }
}

