package com.susancode.fashionblog.service;

import com.susancode.fashionblog.dto.request.LikeRequestDto;
import com.susancode.fashionblog.dto.response.LikesResponse;

public interface LikeService {

    LikesResponse likeOrUnlikePost(LikeRequestDto likeRequestDto);

}
