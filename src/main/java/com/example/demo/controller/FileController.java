//package com.example.demo.config;
//
//import com.example.demo.service.FastApiService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@RestController
//public class FileController {
//
//    // 예시 메소드
//    private final FastApiService fastApiService;
//
//
//    public FileController(FastApiService fastApiService) {
//        this.fastApiService = fastApiService;
//    }
//
//    @PostMapping("/convert-and-send")
//    public Mono<ResponseEntity<String>> sendAudio(@RequestParam("file") MultipartFile m4aFile) {
//        try {
//
//            // 음성 파일을 FastAPI에 전송
//            return fastApiService.sendFileToFastAPI(m4aFile)
//                    .doOnSuccess(response -> System.out.println("Success: " + response))
//                    .map(response -> ResponseEntity.ok().body(response));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred"));
//        }
//    }
//
//
//}
//
