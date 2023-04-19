package com.susancode.fashionblog.controller;

import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.request.VisitorRequestDto;
import com.bctech.fashionista.dto.response.ApiResponse;
import com.bctech.fashionista.dto.response.VisitorResponseDto;
import com.bctech.fashionista.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;


    @PostMapping
    public ResponseEntity<ApiResponse<VisitorResponseDto>> createVisitor(@RequestBody @Valid VisitorRequestDto request) {
        VisitorResponseDto response = visitorService.createVisitor(request);

        return ResponseEntity.ok().body(
                ApiResponse.<VisitorResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @PutMapping("{id:[\\d]+}")
    public ResponseEntity<ApiResponse<VisitorResponseDto>> updateVisitor(@RequestBody VisitorRequestDto request,
                                                                     @PathVariable Long id) {
        VisitorResponseDto response = visitorService.updateVisitor(request, id);

        return ResponseEntity.ok().body(
                ApiResponse.<VisitorResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping("{id:[\\d]+}")
    public  ResponseEntity<ApiResponse<Boolean>> deleteVisitor(@PathVariable Long id) {
        Boolean response = visitorService.deleteVisitor(id);

        return ResponseEntity.ok().body(
                ApiResponse.<Boolean>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<VisitorResponseDto>> loginVisitor(@RequestBody LoginRequestDto request) {

        VisitorResponseDto response = visitorService.fetchVisitor(request);

        return ResponseEntity.ok().body(
                ApiResponse.<VisitorResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

}
