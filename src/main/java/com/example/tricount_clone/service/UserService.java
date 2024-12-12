package com.example.tricount_clone.service;

import com.example.tricount_clone.domain.User;
import com.example.tricount_clone.dto.UserSignUpRequest;
import com.example.tricount_clone.exception.UserAlreadyExistsException;
import com.example.tricount_clone.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public void signUp(UserSignUpRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new UserAlreadyExistsException("이미 사용 중인 사용자 이름입니다.");
		}

		User user = User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.nickname(request.getNickname())
			.build();

		userRepository.save(user);
	}
}
