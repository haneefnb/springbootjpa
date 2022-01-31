package com.tipsol.springbootjpa.dto;

import java.util.Set;

import lombok.Data;

@Data
public class Signup {
	private String name;
    private String username;
    private String password;
    private Set<String> roles;
}
