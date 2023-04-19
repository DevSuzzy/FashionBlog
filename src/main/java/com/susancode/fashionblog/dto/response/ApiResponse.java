package com.susancode.fashionblog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data //Getter, Setter, RequiredArgsConst, ToString, Hash and Equals
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;
    private Object error;
    private final LocalDateTime time = LocalDateTime.now();
}
