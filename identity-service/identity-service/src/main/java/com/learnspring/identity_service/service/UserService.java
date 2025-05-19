package com.learnspring.identity_service.service;

import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.dto.response.UserResponse;
import com.learnspring.identity_service.entity.User;
import com.learnspring.identity_service.exception.AppException;
import com.learnspring.identity_service.exception.ErrorCode;
import com.learnspring.identity_service.mapper.UserMapper;
import com.learnspring.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Tạo constructor cho tất cả các biến được khai báo final trong class
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)   // Tất cả các biến nếu không có khai báo cụ thể thì mặc định sẽ là private final
public class UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    public User createUser(UserCreationRequest request){
        //Alert when we create new user with existed username
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        /* Thay vì:
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        */

        // Ta chỉ cần
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //tạo thêm 1 dòng trong csdl
        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!")));
    }

        public UserResponse updateUser(String id, UserUpdateRequest request){
        //Lấy user đó ra
        User user = userRepository.findById(    id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        userMapper.updateUser(user, request);

        /* Bước này bỏ
        //Update thông tin của user đó
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        */

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
