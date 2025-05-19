package com.learnspring.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

// Người dùng sẽ cung cấp username & password thông qua dto này
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    String username, password;
}
