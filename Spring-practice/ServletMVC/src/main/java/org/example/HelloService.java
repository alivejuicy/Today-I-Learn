package org.example;

import org.springframework.stereotype.Service;
//비즈니스 로직층 Model(Service)
@Service
public class HelloService{
    public String getName(){
        return "alivejuicy";
    }
}
