package com.susancode.fashionblog.service.implementation;

import com.susancode.fashionblog.constants.AuthorType;
import com.susancode.fashionblog.dto.request.CommentRequestDto;
import com.susancode.fashionblog.dto.response.AdminResponseDto;
import com.susancode.fashionblog.dto.response.CommentResponseDto;
import com.susancode.fashionblog.dto.response.PaginateResponse;
import com.susancode.fashionblog.entity.Comment;
import com.susancode.fashionblog.exceptions.customexceptions.AdminNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.CommentNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.PostNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.VisitorNotFoundException;
import com.susancode.fashionblog.repository.AdminRepository;
import com.susancode.fashionblog.repository.CommentRepository;
import com.susancode.fashionblog.repository.PostRepository;
import com.susancode.fashionblog.repository.VisitorRepository;
import com.susancode.fashionblog.service.CommentService;
import com.susancode.fashionblog.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final VisitorRepository visitorRepository;
    private final AdminRepository adminRepository;


    @Override
    public CommentResponseDto createCommentByAdmin(CommentRequestDto commentRequestDto, AdminResponseDto adminResponseDto) {

        var admin = adminRepository.findById(adminResponseDto.getId())
                .orElseThrow(() -> new AdminNotFoundException(adminResponseDto.getId()));

        var post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentRequestDto.getPostId()));

        return ModelMapperUtils.map(commentRepository.save(Comment.builder()
                .post(post)
                .admin(admin)
                .authorType(AuthorType.ADMIN)
                .content(commentRequestDto.getContent())
                .build()), CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto createCommentByVisitor(CommentRequestDto commentRequestDto){
        var visitor = visitorRepository.findById(commentRequestDto.getVisitorId())
                .orElseThrow(() -> new VisitorNotFoundException(commentRequestDto.getVisitorId()));

        var post = postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentRequestDto.getPostId()));

        return ModelMapperUtils.map(commentRepository.save(Comment.builder()
                .post(post)
                .visitor(visitor)
                .authorType(AuthorType.VISITOR)
                .content(commentRequestDto.getContent())
                .build()), CommentResponseDto.class);
    }

    @Override
    public PaginateResponse<CommentResponseDto> fetchCommentOfAPost(Long postId,int start, int limit) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        Page<Comment> comments = commentRepository.findAllByPost(post, PageRequest.of(start, limit));
        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();
    }
    @Override
    public PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorByEmail( String email, int start, int limit){

        var visitor = visitorRepository.findByEmail(email)
                .orElseThrow(() -> new VisitorNotFoundException(email));

        Page<Comment> comments = commentRepository.findAllByVisitor(visitor, PageRequest.of(start, limit));

        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();

    }

    @Override
    public PaginateResponse<CommentResponseDto> getAllCommentsOfVisitorById(Long id, int start, int limit){

        var visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new VisitorNotFoundException(id));

        Page<Comment> comments = commentRepository.findAllByVisitor(visitor, PageRequest.of(start, limit));

        return  PaginateResponse.<CommentResponseDto>builder()
                .content(ModelMapperUtils.mapAll(comments.getContent(), CommentResponseDto.class))
                .totalElements(comments.getTotalElements())
                .build();

    }
    @Override
    public Boolean deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        commentRepository.delete(comment);
        return true;

    }



}


