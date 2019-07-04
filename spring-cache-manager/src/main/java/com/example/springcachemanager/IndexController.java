package com.example.springcachemanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hank (hank@meiyibao.com)
 */
@RequestMapping("/index")
@RestController
public class IndexController {

    @Resource
    private HelloService helloService;

    @GetMapping("/say/{name}")
    public String say(@PathVariable String name) {
        return helloService.sayHello(name);
    }
    @GetMapping("/ordermessage/{status}")
    public int ordermessage(@PathVariable int status) {
        helloService.orderMessage(status);
        return status;
    }
}
