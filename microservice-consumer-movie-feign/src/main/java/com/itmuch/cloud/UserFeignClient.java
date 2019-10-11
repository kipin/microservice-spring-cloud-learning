package com.itmuch.cloud;

import com.itmuch.cloud.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {
    /*
     * 使用Feign的坑：
     * 1.较早版本Feign，不支持GetMapping
     * 2.@PathVariable得设置value
     */
    @GetMapping("/simple2/{id}")
//    @RequestMapping(method = RequestMethod.GET, value = "/stores")
//    public Optional<User> findById(@PathVariable Long id);
    public Optional<User> findById(@PathVariable("id") Long id);

    @PostMapping("/user")
    public User postUser(@RequestBody User user);
}
