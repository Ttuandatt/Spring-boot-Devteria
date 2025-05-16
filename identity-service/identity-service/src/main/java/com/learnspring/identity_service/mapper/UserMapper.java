package com.learnspring.identity_service.mapper;

import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.dto.response.UserResponse;
import com.learnspring.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public  interface UserMapper {
    // Mapstruct sẽ giúp ta generate các bước. Cụ thể thì xem ở UserMapperImpl ở folder target
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);   // @MappingTarget User user, UserUpdateRequest request: giúp map data từ UserUpdateRequest vào Object User

}
