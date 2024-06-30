package com.builtlab.identity_service.service;

import com.builtlab.identity_service.dto.request.ApiResponse;
import com.builtlab.identity_service.dto.request.UserCreationRequest;
import com.builtlab.identity_service.dto.request.UserUpdateRequest;
import com.builtlab.identity_service.dto.response.UserResponse;
import com.builtlab.identity_service.entity.User;
import com.builtlab.identity_service.enums.Role;
import com.builtlab.identity_service.exception.AppException;
import com.builtlab.identity_service.exception.ErrorCode;
import com.builtlab.identity_service.mapper.UserMapper;
import com.builtlab.identity_service.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


// Use constructor instead of using Autowired
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("USER_EXITED");
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
        List<UserResponse> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(userMapper.toUserResponse(e)));
        return list;
    }

    public ApiResponse getUser(String id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1000);
        apiResponse.setResult(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXITED)));
        return apiResponse;
    }

    public ApiResponse<UserResponse> updateUser(String userId, UserUpdateRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(200);
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_EXITED));
        userMapper.updateUser(user, request);
        apiResponse.setResult(userMapper.toUserResponse(userRepository.save(user)));
        return apiResponse;

    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
