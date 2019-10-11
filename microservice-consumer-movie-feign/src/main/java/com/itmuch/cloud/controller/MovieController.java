package com.itmuch.cloud.controller;

import com.itmuch.cloud.UserFeignClient;
import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class MovieController {
//    @Autowired
//    private RestTemplate restTemplate;

//    @Value("${user.userServicePath}")
//    private String userServicePath;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
//        return this.restTemplate.getForObject(this.userServicePath + id, User.class);
        return null;
    }

    @GetMapping("/movie2/{id}")
    public Optional<User> findBiId2(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/test") // xxx/test?id=1&username=zhangsan&name=zhangsan
    public User testPost(@RequestBody User user) {
        return this.userFeignClient.postUser(user);
    }

}
