package com.learnspring.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;
    Date dateOfBirth;
}
