package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

//    @Value("${user.userServicePath}")
//    private String userServicePath;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
//        return this.restTemplate.getForObject(this.userServicePath + id, User.class);

        /*
         * 之前的URL：http://localhost:7900/simple2/
         *
         * 此处的URL的 microservice-provider-user 是种VIP
         * VIP virtual IP
         * HAProxy HeartBeat软件中就有设计VIP
         *
         * 实在理解不了：什么是VIP，记住这样的规律：
         *      请求的服务提供者的Service ID，就是服务提供者配置文件中的 spring.application.name
         */
        return this.restTemplate.getForObject("http://microservice-provider-user/simple2/" + id, User.class);
    }

    @GetMapping("/test")
    public String test() {
        ServiceInstance instance = this.loadBalancerClient.choose("microservice-provider-user");
        System.out.println("111" + instance.getHost() + ":" + instance.getPort() + ":" + instance.getServiceId());

        ServiceInstance instance2 = this.loadBalancerClient.choose("microservice-provider-user2");
        System.out.println("222" + instance2.getHost() + ":" + instance2.getPort() + ":" + instance2.getServiceId());

        return "1";
    }

}
