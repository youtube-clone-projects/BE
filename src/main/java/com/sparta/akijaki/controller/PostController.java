package com.sparta.akijaki.controller;

import com.sparta.akijaki.dto.*;
import com.sparta.akijaki.service.AwsS3Service;
import com.sparta.akijaki.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = {"Post API"})
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    private final AwsS3Service awsS3Service;


    // 전체 게시글 조회
    @GetMapping("/posts")
    @ApiOperation(value = "전체 게시글 조회")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/posts/{postId}")
    @ApiOperation(value = "게시글 상세 조회")
    public PostResponseDto getPost(@PathVariable Long postId, HttpServletRequest httpServletRequest) {
        return postService.getPost(postId,httpServletRequest);
    }

    //게시글 작성
    @PostMapping("/posts")
    @ApiOperation(value = "게시글 작성")
    public PostResponseDto createPost(@RequestParam("title") String title, @RequestParam("content") String content,
                                            @RequestParam("category") String category,
                                            @RequestPart(value = "video",required = false) List<MultipartFile> multipartList,
                                            @RequestPart(value = "image",required = false) List<MultipartFile> multipartFiles, HttpServletRequest request)  {
        String videoFile = awsS3Service.uploadFile(multipartList).get(0);
        String imageFile = awsS3Service.uploadFile(multipartFiles).get(0);
        return postService.createPost(title, content, category, videoFile, imageFile,request);
    }

    //카테고리
    @GetMapping("/posts/category")
    public List<PostResponseDto> getPostCategory(@RequestParam String category) {
        return postService.getPostCategory(category);
    }

//    @PostMapping("/post")
//    @ApiOperation(value = "게시글 작성")tDto test
//    public void createPost(@ModelAttribute TesDto, HttpServletRequest request)  {
//        String imageUrl = awsS3Service.uploadFile(testDto.getFiles()).get(0);
//        System.out.println(testDto.getFiles());
//        System.out.println(testDto.getTitle());
//        return postService.createPost();
//    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    @ApiOperation(value = "게시글 수정")
    public PostResponseDto updatePost(@PathVariable Long postId ,@RequestParam("title") String title, @RequestParam("content") String content,
                                             @RequestParam("category") String category,
                                             @RequestPart(value = "video",required = false) List<MultipartFile> multipartList,
                                             @RequestPart(value = "image",required = false) List<MultipartFile> multipartFiles,HttpServletRequest request) {
        String videoFile = awsS3Service.uploadFile(multipartList).get(0);
        String imageFile = awsS3Service.uploadFile(multipartFiles).get(0);
        return postService.updatePost(postId, title, content, category,videoFile, imageFile,request);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "게시글 삭제")
    public CompleteResponseDto deletePost(@PathVariable Long postId, HttpServletRequest request) {
        return postService.deletePost(postId, request);
    }
    //게시글 검색
    @GetMapping("/posts/search")
    public ResponseEntity<?> searchPost(@RequestParam String search){
        return postService.searchPost(search);
    }
}


