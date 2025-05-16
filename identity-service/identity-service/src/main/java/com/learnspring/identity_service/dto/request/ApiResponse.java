package com.learnspring.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)  // In case the code was successfully executed, normally the message will be null, so using this annotation to not display the null messsage
public class ApiResponse <T>{
    int code=1000; // We standardize code=1000 for successful execution
    String message;
    T result;

}
