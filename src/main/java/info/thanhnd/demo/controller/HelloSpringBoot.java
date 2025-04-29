package info.thanhnd.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
    @GetMapping("/welcome")
    String welcome() {
        return  "Welcome to My First Spring Boot project";
    }
}
