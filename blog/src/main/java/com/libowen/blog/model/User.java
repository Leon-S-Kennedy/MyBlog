package com.libowen.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    public Integer uid;
    public String username;
    @JsonIgnore
    public String password;
    public String avatar;
    public String gitRepo;
}
