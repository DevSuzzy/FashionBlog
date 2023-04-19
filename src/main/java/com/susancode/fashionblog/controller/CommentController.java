package com.susancode.fashionblog.controller;


import com.bctech.fashionista.dto.request.CommentRequestDto;
import com.bctech.fashionista.dto.response.*;
import com.bctech.fashionista.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
 private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommentResponseDto>> createCommentByVisitor(@RequestBody @Valid CommentRequestDto request) {
        CommentResponseDto response = commentService.createCommentByVisitor(request);

        return ResponseEntity.ok().body(
                ApiResponse.<CommentResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }


    @DeleteMapping("{id:[\\d]+}")
    public  ResponseEntity<ApiResponse<Boolean>> deleteComment(@PathVariable Long id) {
        Boolean response = commentService.deleteComment(id);

        return ResponseEntity.ok().body(
                ApiResponse.<Boolean>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @GetMapping("/viewComments/{id:[\\d]+}")
    public ResponseEntity<ApiResponse<PaginateResponse<CommentResponseDto>>> fetchAllCommentsOfAPost(@RequestParam int start, @RequestParam int limit, @PathVariable Long id ) {
        PaginateResponse<CommentResponseDto> response = commentService.fetchCommentOfAPost(id, start, limit);
        return ResponseEntity.ok().body(ApiResponse.<PaginateResponse<CommentResponseDto>>builder()
                .data(response)
                .message("SUCCESS")
                .status(HttpStatus.OK)
                .build());
    }

    @GetMapping("/viewVisitorComments/{id:[\\d]+}")
    public ResponseEntity<ApiResponse<PaginateResponse<CommentResponseDto>>> fetchAllCommentsOfAVisitor(@RequestParam int start, @RequestParam int limit, @PathVariable Long id) {

        PaginateResponse<CommentResponseDto> response = commentService.getAllCommentsOfVisitorById(id,start, limit);
        return ResponseEntity.ok().body(ApiResponse.<PaginateResponse<CommentResponseDto>>builder()
                .data(response)
                .message("SUCCESS")
                .status(HttpStatus.OK)
                .build());
    }

}
