package com.susancode.fashionblog.implementation;

import com.bctech.fashionista.dto.request.PostRequestDTO;
import com.bctech.fashionista.dto.response.PaginateResponse;
import com.bctech.fashionista.dto.response.PostResponseDto;
import com.bctech.fashionista.entity.Admin;
import com.bctech.fashionista.entity.Categories;
import com.bctech.fashionista.entity.Post;
import com.bctech.fashionista.exceptions.customexceptions.AdminNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.PostNotFoundException;
import com.bctech.fashionista.repository.AdminRepository;
import com.bctech.fashionista.repository.CategoriesRepository;
import com.bctech.fashionista.repository.PostRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private AdminRepository adminRepository;

    @MockBean
    private CategoriesRepository categoriesRepository;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestDTO)}
     */
    @Test
    void testCreatePost() {
        when(postRepository.save((Post) any())).thenReturn(new Post());
        when(categoriesRepository.saveAll((Iterable<Categories>) any())).thenReturn(new ArrayList<>());
        when(categoriesRepository.findByTitle((String) any())).thenReturn(Optional.of(new Categories()));
        when(adminRepository.findById((Long) any())).thenReturn(Optional.of(new Admin()));
        PostResponseDto actualCreatePostResult = postServiceImpl
                .createPost(new PostRequestDTO("Dr", "Not all who wander are lost", "Image Path", "Categories", 123L));
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertNull(actualCreatePostResult.getUpdatePostDate());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Image Path", actualCreatePostResult.getImagePath());
        assertNull(actualCreatePostResult.getId());
        assertNull(actualCreatePostResult.getCreationDate());
        verify(postRepository).save((Post) any());
        verify(categoriesRepository).saveAll((Iterable<Categories>) any());
        verify(categoriesRepository).findByTitle((String) any());
        verify(adminRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestDTO)}
     */
    @Test
    void testCreatePost2() {
        when(postRepository.save((Post) any())).thenReturn(new Post());
        when(categoriesRepository.saveAll((Iterable<Categories>) any()))
                .thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(categoriesRepository.findByTitle((String) any()))
                .thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(adminRepository.findById((Long) any())).thenReturn(Optional.of(new Admin()));
        assertThrows(AdminNotFoundException.class, () -> postServiceImpl
                .createPost(new PostRequestDTO("Dr", "Not all who wander are lost", "Image Path", "Categories", 123L)));
        verify(postRepository).save((Post) any());
        verify(categoriesRepository).findByTitle((String) any());
        verify(adminRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestDTO)}
     */
    @Test
    void testCreatePost3() {
        when(postRepository.save((Post) any())).thenReturn(new Post());
        Categories categories = mock(Categories.class);
        doNothing().when(categories).setPost((Post) any());
        Optional<Categories> ofResult = Optional.of(categories);
        when(categoriesRepository.saveAll((Iterable<Categories>) any())).thenReturn(new ArrayList<>());
        when(categoriesRepository.findByTitle((String) any())).thenReturn(ofResult);
        when(adminRepository.findById((Long) any())).thenReturn(Optional.of(new Admin()));
        PostResponseDto actualCreatePostResult = postServiceImpl
                .createPost(new PostRequestDTO("Dr", "Not all who wander are lost", "Image Path", "Categories", 123L));
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertNull(actualCreatePostResult.getUpdatePostDate());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Image Path", actualCreatePostResult.getImagePath());
        assertNull(actualCreatePostResult.getId());
        assertNull(actualCreatePostResult.getCreationDate());
        verify(postRepository).save((Post) any());
        verify(categoriesRepository).saveAll((Iterable<Categories>) any());
        verify(categoriesRepository).findByTitle((String) any());
        verify(categories).setPost((Post) any());
        verify(adminRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestDTO)}
     */
    @Test
    void testCreatePost4() {
        when(postRepository.save((Post) any())).thenReturn(new Post());
        Categories categories = mock(Categories.class);
        doNothing().when(categories).setPost((Post) any());
        Optional<Categories> ofResult = Optional.of(categories);
        when(categoriesRepository.saveAll((Iterable<Categories>) any())).thenReturn(new ArrayList<>());
        when(categoriesRepository.findByTitle((String) any())).thenReturn(ofResult);
        when(adminRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(AdminNotFoundException.class, () -> postServiceImpl
                .createPost(new PostRequestDTO("Dr", "Not all who wander are lost", "Image Path", "Categories", 123L)));
        verify(adminRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostRequestDTO)}
     */
    @Test
    void testCreatePost5() {
        when(postRepository.save((Post) any())).thenReturn(new Post());
        Categories categories = mock(Categories.class);
        doNothing().when(categories).setPost((Post) any());
        Optional<Categories> ofResult = Optional.of(categories);
        when(categoriesRepository.saveAll((Iterable<Categories>) any())).thenReturn(new ArrayList<>());
        when(categoriesRepository.findByTitle((String) any())).thenReturn(ofResult);
        when(adminRepository.findById((Long) any())).thenReturn(Optional.of(new Admin()));
        PostResponseDto actualCreatePostResult = postServiceImpl
                .createPost(new PostRequestDTO("Dr", "Not all who wander are lost", "Image Path", ",", 123L));
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertNull(actualCreatePostResult.getUpdatePostDate());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Image Path", actualCreatePostResult.getImagePath());
        assertNull(actualCreatePostResult.getId());
        assertNull(actualCreatePostResult.getCreationDate());
        verify(postRepository).save((Post) any());
        verify(adminRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    void testGetAllPosts() {
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(postList));
        PaginateResponse<PostResponseDto> actualAllPosts = postServiceImpl.getAllPosts(1, 1);
        assertEquals(postList, actualAllPosts.getContent());
        assertEquals(0L, actualAllPosts.getTotalElements());
        verify(postRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    void testGetAllPosts2() {
        ArrayList<Post> postList = new ArrayList<>();
        postList.add(new Post());
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        PaginateResponse<PostResponseDto> actualAllPosts = postServiceImpl.getAllPosts(1, 1);
        assertEquals(1, actualAllPosts.getContent().size());
        assertEquals(1L, actualAllPosts.getTotalElements());
        verify(postRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    void testGetAllPosts3() {
        ArrayList<Post> postList = new ArrayList<>();
        postList.add(new Post());
        postList.add(new Post());
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        PaginateResponse<PostResponseDto> actualAllPosts = postServiceImpl.getAllPosts(1, 1);
        assertEquals(2, actualAllPosts.getContent().size());
        assertEquals(2L, actualAllPosts.getTotalElements());
        verify(postRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllPosts4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "posts" is null
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        when(postRepository.findAll((Pageable) any())).thenReturn(null);
        postServiceImpl.getAllPosts(1, 1);
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllPosts5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:63)
        //   See https://diff.blue/R013 to resolve this issue.

        when(postRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        postServiceImpl.getAllPosts(-1, 1);
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    void testGetAllPosts6() {
        when(postRepository.findAll((Pageable) any())).thenThrow(new PostNotFoundException(123L));
        assertThrows(PostNotFoundException.class, () -> postServiceImpl.getAllPosts(1, 1));
        verify(postRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllPosts7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: source cannot be null
        //       at org.modelmapper.internal.util.Assert.notNull(Assert.java:53)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:404)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.lambda$mapAll$2(ModelMapperUtils.java:38)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.mapAll(ModelMapperUtils.java:38)
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(null);
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        postServiceImpl.getAllPosts(1, 1);
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    void testGetAllPosts8() {
        Post post = mock(Post.class);
        when(post.getId()).thenReturn(123L);
        when(post.getContent()).thenReturn("Not all who wander are lost");
        when(post.getImagePath()).thenReturn("Image Path");
        when(post.getTitle()).thenReturn("Dr");
        when(post.getCreationDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(post.getUpdatePostDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        PaginateResponse<PostResponseDto> actualAllPosts = postServiceImpl.getAllPosts(1, 1);
        assertEquals(1, actualAllPosts.getContent().size());
        assertEquals(1L, actualAllPosts.getTotalElements());
        verify(postRepository).findAll((Pageable) any());
        verify(post).getId();
        verify(post).getContent();
        verify(post).getImagePath();
        verify(post).getTitle();
        verify(post).getCreationDate();
        verify(post).getUpdatePostDate();
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPosts(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllPosts9() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Error mapping com.bctech.fashionista.entity.Post$MockitoMock$scHDgJFv to com.bctech.fashionista.dto.response.PostResponseDto
        //   1 error
        //       at org.modelmapper.internal.Errors.throwMappingExceptionIfErrorsExist(Errors.java:380)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:80)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.lambda$mapAll$2(ModelMapperUtils.java:38)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.mapAll(ModelMapperUtils.java:38)
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   org.modelmapper.MappingException: ModelMapper mapping errors:
        //   1) Failed to get value from com.bctech.fashionista.entity.Post.getContent()
        //   1 error
        //       at org.modelmapper.internal.Errors.toMappingException(Errors.java:258)
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:106)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.lambda$mapAll$2(ModelMapperUtils.java:38)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.mapAll(ModelMapperUtils.java:38)
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   java.lang.reflect.InvocationTargetException
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.lambda$mapAll$2(ModelMapperUtils.java:38)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.mapAll(ModelMapperUtils.java:38)
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   com.bctech.fashionista.exceptions.customexceptions.AdminNotFoundException: Admin with email: jane.doe@example.org not found
        //       at org.modelmapper.internal.PropertyInfoImpl$MethodAccessor.getValue(PropertyInfoImpl.java:101)
        //       at org.modelmapper.internal.MappingEngineImpl.resolveSourceValue(MappingEngineImpl.java:197)
        //       at org.modelmapper.internal.MappingEngineImpl.propertyMap(MappingEngineImpl.java:170)
        //       at org.modelmapper.internal.MappingEngineImpl.typeMap(MappingEngineImpl.java:151)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:105)
        //       at org.modelmapper.internal.MappingEngineImpl.map(MappingEngineImpl.java:71)
        //       at org.modelmapper.ModelMapper.mapInternal(ModelMapper.java:573)
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:406)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.map(ModelMapperUtils.java:26)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.lambda$mapAll$2(ModelMapperUtils.java:38)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.bctech.fashionista.utils.ModelMapperUtils.mapAll(ModelMapperUtils.java:38)
        //       at com.bctech.fashionista.service.implementation.PostServiceImpl.getAllPosts(PostServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        Post post = mock(Post.class);
        when(post.getId()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(post.getContent()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(post.getImagePath()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(post.getTitle()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(post.getCreationDate()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));
        when(post.getUpdatePostDate()).thenThrow(new AdminNotFoundException("jane.doe@example.org"));

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        postServiceImpl.getAllPosts(1, 1);
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Long)}
     */
    @Test
    void testDeletePost() {
        doNothing().when(postRepository).delete((Post) any());
        when(postRepository.findById((Long) any())).thenReturn(Optional.of(new Post()));
        assertTrue(postServiceImpl.deletePost(123L));
        verify(postRepository).findById((Long) any());
        verify(postRepository).delete((Post) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Long)}
     */
    @Test
    void testDeletePost2() {
        doThrow(new PostNotFoundException(123L)).when(postRepository).delete((Post) any());
        when(postRepository.findById((Long) any())).thenReturn(Optional.of(new Post()));
        assertThrows(PostNotFoundException.class, () -> postServiceImpl.deletePost(123L));
        verify(postRepository).findById((Long) any());
        verify(postRepository).delete((Post) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Long)}
     */
    @Test
    void testDeletePost3() {
        doNothing().when(postRepository).delete((Post) any());
        when(postRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postServiceImpl.deletePost(123L));
        verify(postRepository).findById((Long) any());
    }
}

