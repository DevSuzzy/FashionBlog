package com.susancode.fashionblog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
    private String imagePath;
    private String categories;
    private Long adminId;
}
