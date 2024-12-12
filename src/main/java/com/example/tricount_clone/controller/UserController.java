package com.example.tricount_clone.controller;


import com.example.tricount_clone.dto.UserSignUpRequest;
import com.example.tricount_clone.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserSignUpRequest request) {
		userService.signUp(request);
		return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
	}
}
