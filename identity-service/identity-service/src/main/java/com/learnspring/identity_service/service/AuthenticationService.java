package com.learnspring.identity_service.service;

import com.learnspring.identity_service.dto.request.AuthenticationRequest;
import com.learnspring.identity_service.exception.AppException;
import com.learnspring.identity_service.exception.ErrorCode;
import com.learnspring.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;


    public boolean authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));  // Tìm user nếu k thấy thì ném ra lỗi USER_NOT_FOUND

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);    // Tạo đối tượng PasswordEncoder
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());    // so sánh mật khẩu truyền vào được mã hóa với mật khẩu của user, trả về kết quả so sánh
    }
}
