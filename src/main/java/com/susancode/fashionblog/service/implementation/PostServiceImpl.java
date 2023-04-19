package com.susancode.fashionblog.service.implementation;

import com.susancode.fashionblog.dto.request.CategoryDto;
import com.susancode.fashionblog.dto.request.PostRequestDTO;
import com.susancode.fashionblog.dto.response.PaginateResponse;
import com.susancode.fashionblog.dto.response.PostResponseDto;
import com.susancode.fashionblog.entity.Categories;
import com.susancode.fashionblog.entity.Post;
import com.susancode.fashionblog.exceptions.customexceptions.AdminNotFoundException;
import com.susancode.fashionblog.exceptions.customexceptions.PostNotFoundException;
import com.susancode.fashionblog.repository.AdminRepository;
import com.susancode.fashionblog.repository.CategoriesRepository;
import com.susancode.fashionblog.repository.PostRepository;
import com.susancode.fashionblog.service.PostService;
import com.susancode.fashionblog.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoriesRepository categoriesRepository;
    private final AdminRepository AdminRepository;


    @Override
    public PostResponseDto createPost(PostRequestDTO postRequestDto) {

        var admin = AdminRepository.findById(postRequestDto.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException(postRequestDto.getAdminId()));


        var post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .imagePath(postRequestDto.getImagePath())
                .admin(admin)
                .build();

        postRepository.save(post);
        makeCategoryForPost(postRequestDto.getCategories(), post);



        return ModelMapperUtils.map(post, PostResponseDto.class);
    }


   @Override
    public PaginateResponse<PostResponseDto> getAllPosts(int start, int limit){

        Page<Post> posts = postRepository.findAll(PageRequest.of(start, limit));

        return  PaginateResponse.<PostResponseDto>builder()
                .content(ModelMapperUtils.mapAll(posts.getContent(), PostResponseDto.class))
                .totalElements(posts.getTotalElements())
                .build();

    }

    @Override
    public PaginateResponse<PostResponseDto> getAllPostsByCategory(CategoryDto categoryDto, int start, int limit){


        Page<Post> posts = postRepository.findAllByCategories_Title(categoryDto.getTitle(),PageRequest.of(start, limit));

        for(Post post: posts){
           log.info("Post: {}", post.getTitle());
        }


        return  PaginateResponse.<PostResponseDto>builder()
                .content(ModelMapperUtils.mapAll(posts.getContent(), PostResponseDto.class))
                .totalElements(posts.getTotalElements())
                .build();


    }

    @Override
    public PostResponseDto updatePost(PostRequestDTO postRequestDTO, Long id) {
        log.info("Post about to find: {}", postRequestDTO.getTitle());
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new PostNotFoundException(id);
        });
        log.info("Post found: {}", post.getTitle());
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setImagePath(postRequestDTO.getImagePath());
        var _post = postRepository.save(post);

        makeCategoryForPost(postRequestDTO.getCategories(), post);

        return ModelMapperUtils.map(post, PostResponseDto.class);
    }


    @Override
    public Boolean deletePost(Long id) {
        Post user = postRepository.findById(id).orElseThrow(() -> {
            throw new PostNotFoundException(id);
        });
        postRepository.delete(user);
        return true;
    }

    @Override
    public Long getAuthorOfPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new PostNotFoundException(id);
        });
        return post.getAdmin().getId();
    }


    private Post makeCategoryForPost(String categoryString, Post post) {

        // to cater for multiple categories, we need to split the string into a set of categories
        var s = categoryString.toUpperCase().split(",");
        // we need to convert the string array into a set of categories
        var categoryList = Arrays.stream(s)
                .map(category -> categoriesRepository.findByTitle(category).orElse(Categories.builder()
                        .title(category)
                        .build())).collect(Collectors.toSet());

        for (Categories category : categoryList) {
            category.setPost(post);

            categoriesRepository.saveAll(categoryList);


        }
        return post;

    }


}
