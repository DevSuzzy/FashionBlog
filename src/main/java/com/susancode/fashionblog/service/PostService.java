package com.susancode.fashionblog.service;

import com.susancode.fashionblog.dto.request.CategoryDto;
import com.susancode.fashionblog.dto.request.PostRequestDTO;
import com.susancode.fashionblog.dto.response.PaginateResponse;
import com.susancode.fashionblog.dto.response.PostResponseDto;

public interface PostService {

    PostResponseDto createPost(PostRequestDTO postRequestDto);

    PaginateResponse<PostResponseDto> getAllPosts(int start, int limit);

    PaginateResponse<PostResponseDto> getAllPostsByCategory(CategoryDto categoryDto, int start, int limit);

    PostResponseDto updatePost(PostRequestDTO postRequestDTO, Long id);

    Boolean deletePost(Long id);

    Long getAuthorOfPost(Long id);
}
