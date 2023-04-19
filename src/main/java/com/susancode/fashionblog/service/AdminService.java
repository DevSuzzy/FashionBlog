package com.susancode.fashionblog.service;

import com.susancode.fashionblog.dto.request.AdminRequestDto;
import com.susancode.fashionblog.dto.request.LoginRequestDto;
import com.susancode.fashionblog.dto.response.AdminResponseDto;

public interface AdminService {
    AdminResponseDto createUser(AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, Long id);

    Boolean deleteAdmin(Long id);

    AdminResponseDto fetchAdmin (LoginRequestDto loginRequestDto);

}
