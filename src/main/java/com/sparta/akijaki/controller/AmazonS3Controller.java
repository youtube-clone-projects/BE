package com.sparta.akijaki.controller;

import com.sparta.akijaki.service.AwsS3Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//파일 업로드 경우 GET방식을 사용하고 파일 삭제의 경우 DELETE를 사용
@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

    /**
     * Amazon S3에 파일 업로드
     * @return 성공 시 200 Success와 함께 업로드 된 파일의 파일명 리스트 반환
     */
    @ApiOperation(value = "Amazon S3에 파일 업로드", notes = "Amazon S3에 파일 업로드")
    @PostMapping("/file")
    public List<String> uploadFile(@ApiParam(value = "파일들(여러 파일 업로드 가능)", required = true) @RequestPart (value="file", required = false) List<MultipartFile> multipartFiles) {
        return awsS3Service.uploadFile(multipartFiles);
    }

    /**
     * Amazon S3에 업로드 된  파일을 삭제
     * @return 성공 시 200 Success
     */
    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 파일 삭제")
    @DeleteMapping("/file")
    public void deleteFile(@ApiParam(value = "파일 하나 삭제", required = true) @RequestParam String fileName) {
          awsS3Service.deleteFile(fileName);
    }
}
