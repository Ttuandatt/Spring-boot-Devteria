package com.learnspring.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
