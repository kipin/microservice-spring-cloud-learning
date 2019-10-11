package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
 * @RestController org.springframework.web.bind.annotation.RestController
 * 是个组合注解 spring4.0之后出现的
 * 相当于 @Controller + @ResponseBody
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /*
     * @GetMapping org.springframework.web.bind.annotation.GetMapping
     * 是个组合注解 spring4.3之后出现的
     * 相当于 @RequestMapping(value = "/xxx", method = RequestMethod.GET)
     */
    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Long id) {
        /*
         * getOne() 需要注意：
         * 1. 需要在实体类上加上“@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})”，
         *      不然fasterxml.jackson将对象转换为json报错。不管这条数据有没有查到值，必须先加上这个注解，不然报错。
         * 2. id不存在时，抛出异常；id存在返回值。可能因为spring-boot版本问题，没有findOne(id)方法。
         *      自从spring boot 2.0以后，由于用上了Java 8 的Option，废除了findOne(Id)
         */
        return this.userRepository.getOne(id); // id不存在时抛异常
    }

    @GetMapping("/simple2/{id}")
    public Optional<User> findById2(@PathVariable Long id) {
        return this.userRepository.findById(id);
    }

    @GetMapping(value = "/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
        return instance.getHomePageUrl(); // http://192.168.1.102:7900/

        /*
         * getNextServerFromEureka() 方法的第一个参数，就是Eureka Server主页里instances的Application name.
         * 所以，通过这个Application名称就可以知道这个IP地址、端口。
         */
    }

    @PostMapping(value="/user")
    public User postUser(@RequestBody User user) {
        return user;
    }
}
