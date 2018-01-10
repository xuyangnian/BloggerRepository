package com.fnd.blogger.manager.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Custom implements Serializable {
    private static final long serialVersionUID = 634613161637045839L;
    @Id
    private String id;
    private String name;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
