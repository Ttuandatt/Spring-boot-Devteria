package com.learnspring.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

//@Getter   // Lombok sẽ tự tạo ra getter dựa theo các attributes ta đã khai báo. Muốn kiểm tra thì vào folder target sẽ thấy getter vẫn được tạo như bình thường
//@Setter   // Lombok sẽ tự tạo ra getter dựa theo các attributes ta đã khai báo. Muốn kiểm tra thì vào folder target sẽ thấy setter vẫn được tạo như bình thường
@Data       // Sẽ tạo tự động cả getter, setter, constructor, phương thức toString & equalsAndHashCode
@NoArgsConstructor  // Quy định cho @Data bên trên tạo constructor không tham số
@AllArgsConstructor // Quy định cho @Data bên trên tạo constructor đầy đủ tham số
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) // Tất cả các field hay biến toàn cục trong class này nếu ta không xác định cụ level cụ thể thì chúng mặc định sẽ ở mức private. Khí đó ta không cần khai báo từ khóa Private trước các biến
public class UserCreationRequest {
    @Size(min = 3, message="INVALID_USERNAME")
    String username;
    @Size(min = 3, message="INVALID_PASSORD")
    String password;
    String firstName;
    String lastName;
    Date dateOfBirth;


}
