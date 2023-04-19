package com.susancode.fashionblog.controller;

import com.bctech.fashionista.dto.request.*;
import com.bctech.fashionista.dto.response.*;
import com.bctech.fashionista.service.LikeService;
import com.bctech.fashionista.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final LikeService likeService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@RequestBody @Valid PostRequestDTO request) {

        log.info(" About to use the Request to make a Post: {}", request.getAdminId());
        log.info(" About to use the Request to make a Post with categories: {}", request.getCategories());
        PostResponseDto response = postService.createPost(request);

        return ResponseEntity.ok().body(
                ApiResponse.<PostResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @PutMapping("{postId:[\\d]+}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@RequestBody PostRequestDTO request,
                                                                     @PathVariable Long postId) {
        // verify if the author is the same as the logged in user
        var authorId = postService.getAuthorOfPost(postId);
        log.info("Author of post: {}", authorId);
        log.info("Post id: {}", postId);
        if (!request.getAdminId().equals(authorId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    ApiResponse.<PostResponseDto>builder()
                            .message("You are not the author of this post")
                            .status(HttpStatus.FORBIDDEN)
                            .build()
            );
        }

        PostResponseDto response = postService.updatePost(request, postId);

        return ResponseEntity.ok().body(
                ApiResponse.<PostResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping("{id:[\\d]+}")
    public  ResponseEntity<ApiResponse<Boolean>> deletePost(@PathVariable Long id) {
        Boolean response = postService.deletePost(id);

        return ResponseEntity.ok().body(
                ApiResponse.<Boolean>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @GetMapping("/viewAllPosts")
    public ResponseEntity<ApiResponse<PaginateResponse<PostResponseDto>>> fetchAllPosts(@RequestParam int start, @RequestParam int limit) {
        PaginateResponse<PostResponseDto> response = postService.getAllPosts(start, limit);
        return ResponseEntity.ok().body(ApiResponse.<PaginateResponse<PostResponseDto>>builder()
                .data(response)
                .message("SUCCESS")
                .status(HttpStatus.OK)
                .build());
    }

    @GetMapping("/viewAllPostsByCategory")
    public ResponseEntity<ApiResponse<PaginateResponse<PostResponseDto>>> fetchAllPosts(@RequestParam int start, @RequestParam int limit, @RequestParam String category) {
         var categories = CategoryDto.builder().title(category).build();
        PaginateResponse<PostResponseDto> response = postService.getAllPostsByCategory(categories,start, limit);
        return ResponseEntity.ok().body(ApiResponse.<PaginateResponse<PostResponseDto>>builder()
                .data(response)
                .message("SUCCESS")
                .status(HttpStatus.OK)
                .build());
    }

    @PutMapping("/like/{id:[\\d]+}")
    public ResponseEntity<ApiResponse<LikesResponse>> likeOrUnlikeAPost( @PathVariable Long id, @RequestBody VisitorResponseDto visitorResponseDto) {
        var request = LikeRequestDto.builder().postId(id).visitorId(visitorResponseDto.getId()).build();
        LikesResponse response = likeService.likeOrUnlikePost(request);
        return ResponseEntity.ok().body(
                ApiResponse.<LikesResponse>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }


}
