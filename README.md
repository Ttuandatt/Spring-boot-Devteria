Tổng quan cách hoạt động của identity-service:
=
🧱 1. Kiến trúc tổng thể
=
Project của bạn tuân theo kiến trúc 3 tầng (Three-layered architecture):

Controller Layer  →  Service Layer  →  Repository Layer (Database)
        ↓                     ↓                 ↓
     DTO / Request      Entity / Mapper     JPA / Query
        ↑
  Exception Handling

  
📦 2. Luồng hoạt động chi tiết
=
🧩 Bước 1: Client gửi yêu cầu tới API
=
Ví dụ: Gửi POST /users với nội dung JSON tạo user.


🌐 Bước 2: Controller xử lý đầu vào
=
UserController sẽ:

Nhận dữ liệu UserCreationRequest từ @RequestBody.

Gọi UserService.createUser() để xử lý logic.

Trả kết quả ra dạng ApiResponse<User>.

Ví dụ:
@PostMapping
ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request)


🔧 Bước 3: Service xử lý logic nghiệp vụ
=
UserService chịu trách nhiệm chính:

Kiểm tra nghiệp vụ: Username đã tồn tại chưa.

Map DTO → Entity: Dùng UserMapper để chuyển UserCreationRequest thành User.

Lưu xuống DB: Gọi UserRepository.save().


if(userRepository.existsByUsername(request.getUsername()))
    throw new AppException(ErrorCode.USER_EXISTED);

User user = userMapper.toUser(request);
return userRepository.save(user);
Các chức năng khác như getUserById, updateUser, deleteUser cũng đi theo flow này.


🗃️ Bước 4: Repository thao tác với database
=
UserRepository mở rộng từ JpaRepository<User, String> nên:

Có sẵn các hàm CRUD.

Bạn có thể thêm hàm tùy chỉnh như existsByUsername.

boolean existsByUsername(String username);


🔄 Bước 5: Mapper chuyển đổi dữ liệu
=
UserMapper (dùng MapStruct):

Chuyển đổi giữa Entity ↔ DTO:

User toUser(UserCreationRequest request);
UserResponse toUserResponse(User user);
void updateUser(@MappingTarget User user, UserUpdateRequest request);


⚠️ Bước 6: Xử lý lỗi
=
GlobalExceptionHandler:

Bắt các exception trong toàn bộ hệ thống.

Trả về mã lỗi & message tương ứng (ErrorCode).

Các lỗi được chuẩn hóa trong ApiResponse.

@ExceptionHandler(value = AppException.class)
ResponseEntity<ApiResponse> handlingAppException(AppException e)


📤 Bước 7: Trả dữ liệu cho client
=
Tất cả dữ liệu trả về được gói trong ApiResponse<T>:

json
Copy
Edit
{
  "code": 1000,
  "message": null,
  "result": {
    "id": "...",
    "username": "...",
    ...
  }
}
🧠 Tổng kết chức năng chính
Tính năng	Endpoint	Xử lý
Tạo user	POST /users	Validate → Map → Save
Lấy danh sách user	GET /users	Trả List<User>
Lấy user theo ID	GET /users/{id}	Tìm → Map → Trả
Cập nhật user	PUT /users/{id}	Tìm → Map → Save
Xóa user	DELETE /users/{id}	Gọi deleteById()
