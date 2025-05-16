package com.learnspring.identity_service.controller;

import com.learnspring.identity_service.dto.request.ApiResponse;
import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.dto.response.UserResponse;
import com.learnspring.identity_service.entity.User;
import com.learnspring.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;



    //Tạo user. Đây được tính là 1 API, đơn giản chưa =))))
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) { //1 endpoint sẽ nhận data từ users, thì để map data từ request vào object thì ta dùng @RequestBody map data của body vào object UserCreationRequest
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    //Lấy danh sách user
    @GetMapping
    List<User> getUser(){
        return userService.getUser();
    }

    //Lấy user by Id
    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    //Cập nhật thông tin user
    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest updateRequest){
        return userService.updateUser(userId, updateRequest);
    }

    //Xóa user
    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }
}

