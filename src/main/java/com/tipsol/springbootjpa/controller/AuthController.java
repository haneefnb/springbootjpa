package com.tipsol.springbootjpa.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipsol.springbootjpa.commondto.ErrorResponse;
import com.tipsol.springbootjpa.commondto.JWTAuthResponse;
import com.tipsol.springbootjpa.commondto.SuccessResponse;
import com.tipsol.springbootjpa.dao.Role;
import com.tipsol.springbootjpa.dao.User;
import com.tipsol.springbootjpa.dto.Login;
import com.tipsol.springbootjpa.dto.Signup;
import com.tipsol.springbootjpa.repository.RoleRepository;
import com.tipsol.springbootjpa.repository.UserRepository;
import com.tipsol.springbootjpa.security.JwtTokenProvider;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleReostory;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/signup")
	@ApiOperation(value = "REST API used for User Registration")
	public ResponseEntity<?> registerUser(@RequestBody Signup userDto) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			return new ResponseEntity<>(new ErrorResponse("INVALID_INPUT", "Username Already Exist"),
					HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRoles(getRolesOfUser(userDto));
		userRepository.save(user);

		return new ResponseEntity<>(new SuccessResponse("SUCESS", "User Created Successfully!!"), 
				HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> loginUser(@RequestBody Login loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTAuthResponse(token));
	}
	
	private Set<Role> getRolesOfUser(Signup userDto){
		Set<Role> roles = new HashSet<>();
		for(String r:userDto.getRoles()) {
			Optional<Role> roleOption = roleReostory.findByName(r);
			if(!roleOption.isEmpty()) {
				roles.add(roleOption.get());
			}
		}
		return roles;
	}

}
