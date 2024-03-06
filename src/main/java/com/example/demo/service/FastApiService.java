//package com.example.demo.service;
//
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import java.io.IOException;
//
//@Service
//public class FastApiService {
//    private final WebClient webClient;
//
//    public FastApiService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://localhost:8000")
//                .defaultHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE)
//                .build();
//    }
//
//    public Mono<String> sendFileToFastAPI(MultipartFile voiceFile) throws IOException {
//        Resource resource = new ByteArrayResource(voiceFile.getBytes()) {
//            @Override
//            public String getFilename() {
//                return voiceFile.getOriginalFilename();
//            }
//        };
//
//        return webClient.post()
//                .uri("/upload-wav")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                // .body(BodyInserters.fromValue(wavFile.getBytes()))
//                .body(BodyInserters.fromMultipartData("file", resource))
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//}
