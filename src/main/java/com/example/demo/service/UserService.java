package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; 

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.enums.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 👇 injeta UserRepository e PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Buscar todos
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();
    }

    // Buscar por ID
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    // Criar novo usuário
    public UserResponse createUser(UserRequest request) {
        String roleStr = request.getRole() != null ? request.getRole() : "END_USER"; // 👈 padrão
        Role role = Role.valueOf(roleStr.toUpperCase());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 👈 senha codificada
                .role(role)
                .build();

        return UserResponse.fromEntity(userRepository.save(user));
    }

    // Atualizar usuário existente
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // 👇 só atualiza se a senha for informada
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        String roleStr = request.getRole() != null ? request.getRole() : "END_USER";
        Role role = Role.valueOf(roleStr.toUpperCase());
        user.setRole(role);

        return UserResponse.fromEntity(userRepository.save(user));
    }

    // Deletar usuário
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        userRepository.delete(user);
    }
}

