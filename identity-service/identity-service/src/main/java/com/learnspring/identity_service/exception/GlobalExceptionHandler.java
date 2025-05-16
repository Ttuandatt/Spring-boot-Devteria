package com.learnspring.identity_service.exception;

import com.learnspring.identity_service.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This is a Fallback Exception Handler method which is used when there are some exceptions that occurred but were not handled in methods below.
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        // This is the Exception Handler for when there is an error, so we don't get result as there is no ressult

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception e){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        // This is the Exception Handler for when there is an error, so we don't get result as there is no ressult

        return ResponseEntity.badRequest().body(apiResponse);
    }



    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e){
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(e.getMessage());
        // This is the Exception Handler for when there is an error, so we don't get result as there is no result

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException e){
        String enumKey = e.getFieldError().getDefaultMessage();


        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try{
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException exception){

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResponse> hadleResourceNotFoundException(NoResourceFoundException e){
        String enumKey = e.getCause().getMessage();

        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;

        try{
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException exception){

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

}

/*
Ban đầu: errorCode được gán giá trị mặc định là ErrorCode.INVALID_KEY.

Trong try-catch: Chương trình sẽ thử tìm trong enum ErrorCode xem có giá trị nào trùng với chuỗi enumKey hay không:

Nếu có, errorCode sẽ được cập nhật thành giá trị tương ứng (ErrorCode.valueOf(enumKey)).

Nếu không có, IllegalArgumentException xảy ra, khối catch chạy nhưng không làm gì, và errorCode vẫn giữ nguyên giá trị mặc định là INVALID_KEY.
*/