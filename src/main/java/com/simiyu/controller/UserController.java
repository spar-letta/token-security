/**
 * 
 */
package com.simiyu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simiyu.model.User;
import com.simiyu.model.UserRequest;
import com.simiyu.model.UserResponse;
import com.simiyu.service.IUserService;
import com.simiyu.util.JwtUtil;

/**
 * @author enock
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService iuserService;
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> createUser(@RequestBody User userDetails){
		
		long id = iuserService.saveUser(userDetails);
		String body = "User '"+id+"' saved";
		
		return ResponseEntity.ok(body);
	}
	
	//2. Validate User and create token (login)
	@PostMapping(value = "/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request){
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), 
				request.getPassword()
				));
		
		String tokenString = util.generateToken(request.getUsername());
		
		return ResponseEntity.ok(new UserResponse(tokenString,"success !! Generated By ENOCK"));
			
	}
	

	@PostMapping("/welcome")
	public ResponseEntity<String> accessData(Principal p){
		return ResponseEntity.ok("Hello user" + "" +p.getName());
	}
	

	@GetMapping(value="/getdata")
	public String adminPing(){
        return "Only Admins Can Read This";
    }
	
	

	@GetMapping(value="/getdata2")
	public String userPing(){
        return "Any User Can Read This";
    }
}
