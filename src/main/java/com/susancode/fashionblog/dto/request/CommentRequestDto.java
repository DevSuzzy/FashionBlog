package com.susancode.fashionblog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentRequestDto {
    private String content;
    private Long postId;
    private Long visitorId;


}
