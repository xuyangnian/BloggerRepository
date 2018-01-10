package com.fnd.blogger.manager.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = -8953908150128970869L;
    @Id
    @ApiModelProperty(value = "用户ID", required = true, example = "123456789")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "用户名称", required = true, example = "yangyang")
    @Column(name = "NAME", nullable = false)
    private String name;

    @ApiModelProperty(value = "用户密码", required = true, example = "123456789")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
