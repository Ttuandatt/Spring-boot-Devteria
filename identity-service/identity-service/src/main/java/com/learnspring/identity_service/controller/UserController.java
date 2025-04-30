package com.learnspring.identity_service.controller;

import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.entity.User;
import com.learnspring.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



    //Tạo user. Đây được tính là 1 API, đơn giản chưa =))))
    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) { //1 endpoint sẽ nhận data từ users, thì để map data từ request vào object thì ta dùng @RequestBody map data của body vào object UserCreationRequest
        return userService.createUser(request);
    }

    //Lấy danh sách user
    @GetMapping
    List<User> getUser(){
        return userService.getUser();
    }

    //Lấy user by Id
    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    //Cập nhật thông tin user
    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest updateRequest){
        return userService.updateUser(userId, updateRequest);
    }

    //Xóa user
    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }
}

