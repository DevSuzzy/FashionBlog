package com.susancode.fashionblog.service.implementation;


import com.susancode.fashionblog.dto.request.LikeRequestDto;
import com.susancode.fashionblog.dto.response.LikesResponse;
import com.susancode.fashionblog.entity.Likes;
import com.susancode.fashionblog.exceptions.customexceptions.PostNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.VisitorNotFoundException;
import com.susancode.fashionblog.repository.LikeRepository;
import com.susancode.fashionblog.repository.PostRepository;
import com.susancode.fashionblog.repository.VisitorRepository;
import com.susancode.fashionblog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final VisitorRepository visitorRepository;


    @Override
    public LikesResponse likeOrUnlikePost(LikeRequestDto likeRequestDto) {
        LikesResponse likesResponse = new LikesResponse();
        //unlike a post when visitor tries to like a post again
        // check if like exist, if like does not exist : create like
        var like = likeRepository.findByVisitorIdAndPostId(likeRequestDto.getVisitorId(), likeRequestDto.getPostId());

        if (like.isPresent()) {
            likeRepository.delete(like.get());
            likesResponse.setIsLiked(false);
            return likesResponse;
        }
       else{ createLikeForPost(likeRequestDto);
        likesResponse.setIsLiked(true);
        return likesResponse;}
    }

    private void createLikeForPost(LikeRequestDto likeRequestDto) {
        var post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(likeRequestDto.getPostId()));

        var visitor = visitorRepository.findById(likeRequestDto.getVisitorId())
                .orElseThrow(() -> new VisitorNotFoundException(likeRequestDto.getVisitorId()));

        likeRepository.save(Likes.builder()
                .post(post)
                .visitor(visitor)
                .build());
    }




}
