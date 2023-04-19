package com.susancode.fashionblog.service;

import com.susancode.fashionblog.dto.request.CommentRequestDto;
import com.susancode.fashionblog.dto.response.AdminResponseDto;
import com.susancode.fashionblog.dto.response.CommentResponseDto;
import com.susancode.fashionblog.dto.response.PaginateResponse;

public interface CommentService {
    CommentResponseDto createCommentByAdmin(CommentRequestDto commentRequestDto, AdminResponseDto adminResponseDto);


    CommentResponseDto createCommentByVisitor(CommentRequestDto commentRequestDto);

    PaginateResponse<CommentResponseDto> fetchCommentOfAPost(Long postId, int start, int limit);

    PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorByEmail(String email, int start, int limit);

    PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorById(Long id, int start, int limit);

    Boolean deleteComment(Long commentId);
}
