package com.itmuch.cloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity //使用spring-data-jpa注解
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class User /*implements Serializable*/ {
    @Id // spring-data-jpa注解
    @GeneratedValue(strategy = GenerationType.AUTO) // spring-data-jpa注解
    private Long id;
    @Column // spring-data-jpa注解
    private String username;
    @Column
    private String name;
    @Column
    private Short age;
    @Column
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
