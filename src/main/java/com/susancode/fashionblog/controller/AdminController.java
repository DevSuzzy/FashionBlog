package com.susancode.fashionblog.controller;

import com.bctech.fashionista.dto.request.AdminRequestDto;
import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.dto.response.ApiResponse;
import com.bctech.fashionista.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse<AdminResponseDto>> createAdmin(@RequestBody @Valid AdminRequestDto request) {
        log.info(" About to use the Request to make an Admin: {}", request.getEmail());
        AdminResponseDto response = adminService.createUser(request);

        return ResponseEntity.ok().body(
                ApiResponse.<AdminResponseDto>builder()
                .data(response)
                .message("SUCCESS")
                .status(HttpStatus.CREATED)
                .build()
        );
    }

    @PutMapping("{id:[\\d]+}")
    public ResponseEntity<ApiResponse<AdminResponseDto>> updateAdmin(@RequestBody AdminRequestDto request,
                                                                @PathVariable Long id) {

        log.info(" About to update the admin with email details: {}", request.getEmail());
        AdminResponseDto response = adminService.updateAdmin(request, id);

        return ResponseEntity.ok().body(
                ApiResponse.<AdminResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping("{id:[\\d]+}")
    public  ResponseEntity<ApiResponse<Boolean>> deleteAdmin(@PathVariable Long id) {
        Boolean response = adminService.deleteAdmin(id);

        return ResponseEntity.ok().body(
                ApiResponse.<Boolean>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminResponseDto>> loginAdmin(@RequestBody LoginRequestDto request) {

        AdminResponseDto response = adminService.fetchAdmin(request);

        return ResponseEntity.ok().body(
                ApiResponse.<AdminResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }



}