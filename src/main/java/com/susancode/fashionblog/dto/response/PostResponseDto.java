package com.susancode.fashionblog.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String imagePath;
    private LocalDateTime creationDate;
    private LocalDateTime updatePostDate;

}
