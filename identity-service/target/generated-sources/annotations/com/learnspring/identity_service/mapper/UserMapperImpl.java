package com.learnspring.identity_service.mapper;

import com.learnspring.identity_service.dto.request.UserCreationRequest;
import com.learnspring.identity_service.dto.request.UserUpdateRequest;
import com.learnspring.identity_service.dto.response.UserResponse;
import com.learnspring.identity_service.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setDateOfBirth( request.getDateOfBirth() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dateOfBirth( user.getDateOfBirth() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setDateOfBirth( request.getDateOfBirth() );
    }
}
