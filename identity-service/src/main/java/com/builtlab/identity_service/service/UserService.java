package com.builtlab.identity_service.service;

import com.builtlab.identity_service.dto.request.UserCreationRequest;
import com.builtlab.identity_service.dto.request.UserUpdateRequest;
import com.builtlab.identity_service.entity.User;
import com.builtlab.identity_service.exception.AppException;
import com.builtlab.identity_service.exception.ErrorCode;
import com.builtlab.identity_service.mapper.UserMapper;
import com.builtlab.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("USER_EXITED");
        }

        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXITED));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

       return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}