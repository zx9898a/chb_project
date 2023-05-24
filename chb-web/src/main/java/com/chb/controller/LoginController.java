package com.chb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chb.jwt.service.JwtUserDetailsService;
import com.chb.jwt.vo.JwtRequest;
import com.chb.utils.JwtTokenUtil;
import com.chb.vo.BasicOut;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/users")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	Gson GSON = new GsonBuilder().create();

	@PostMapping("/login")
	public String authenticationUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
		BasicOut vo = new BasicOut();
		String resault;
		try {
			log.info("authentication user");
			// select member
			// create token
			// send code/ token/ messages
			Map data = new HashMap<>();
			String token = createAuthenticationToken(authenticationRequest);
			data.put("accessToken", token);
			vo.setData(data);
			vo.setCode(20000);
			resault = GSON.toJson(vo);
			log.info("response: " + resault);
			return resault;
		} catch (Exception e) {
			vo.setStatus("e");
			vo.setCode(50008);
			vo.setMessage("error: " + e.getMessage());
			resault = GSON.toJson(vo);
			log.error("response: " + resault);
			return resault;
		}
	}

	private String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		String token;
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUser(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		token = jwtTokenUtil.generateToken(userDetails);
		log.info("generateToken :" + token);
		return token;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
