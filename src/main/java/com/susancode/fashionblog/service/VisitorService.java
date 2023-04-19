package com.susancode.fashionblog.service;

import com.susancode.fashionblog.dto.request.LoginRequestDto;
import com.susancode.fashionblog.dto.request.VisitorRequestDto;
import com.susancode.fashionblog.dto.response.VisitorResponseDto;


public interface VisitorService {


    VisitorResponseDto createVisitor(VisitorRequestDto visitorRequestDto);

    VisitorResponseDto updateVisitor(VisitorRequestDto visitorRequestDto, Long id);

    Boolean deleteVisitor(Long id);

    VisitorResponseDto getVisitorByEmail(String email);

    VisitorResponseDto fetchVisitor(LoginRequestDto loginRequestDto);
}
