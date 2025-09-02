package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dto.request.UserRequest;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.enums.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(UserResponse::fromEntity).toList();
	}

	public UserResponse getUserById(Long id) {
		return userRepository.findById(id).map(UserResponse::fromEntity)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
	}

	public UserResponse createUser(UserRequest request) {
		Role role = Role.valueOf(request.getRole().toUpperCase()); // converte String para Role

		User user = User.builder().name(request.getName()).email(request.getEmail()).password(request.getPassword())
				.role(role).build();

		return UserResponse.fromEntity(userRepository.save(user));
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
		userRepository.delete(user);
	}
}