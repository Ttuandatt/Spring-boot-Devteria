package com.learnspring.identity_service.service;

import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.entity.User;
import com.learnspring.identity_service.exception.AppException;
import com.learnspring.identity_service.exception.ErrorCode;
import com.learnspring.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        //Alert when we create new user with existed username
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());

        return userRepository.save(user);   //tạo thêm 1 dòng trong csdl
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User updateUser(String id, UserUpdateRequest request){
        //Lấy user đó ra
        User user = getUserById(id);

        //Update thông tin của user đó
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());

        return userRepository.save(user);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
