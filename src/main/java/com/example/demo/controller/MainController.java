package com.example.demo.controller;

import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping({"", "/", "/index", "/home", "/main"})
    public ResponseEntity<?> displayMainPage(@RequestParam(required = false) Long id, @RequestParam(required = false) String uuid) {
        // 이 부분은 실제 프로젝트 요구사항에 따라 반환할 객체를 조정해야 합니다.
        return ResponseEntity.ok(this.mainService.displayMainPage(id, uuid));
    }

    @PostMapping("/room")
    public ResponseEntity<?> processRoomSelection(@RequestParam("id") String sid, @RequestParam("uuid") String uuid) {
        // 여기서는 BindingResult를 처리하는 부분을 생략하고 직접 구현해야 합니다.
        return ResponseEntity.ok(this.mainService.processRoomSelection(sid, uuid, null));
    }

    @GetMapping("/room/{sid}/user/{uuid}")
    public ResponseEntity<?> displaySelectedRoom(@PathVariable("sid") String sid, @PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.mainService.displaySelectedRoom(sid, uuid));
    }

    @GetMapping("/room/{sid}/user/{uuid}/exit")
    public ResponseEntity<?> processRoomExit(@PathVariable("sid") String sid, @PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.mainService.processRoomExit(sid, uuid));
    }

    @GetMapping("/room/random")
    public ResponseEntity<?> requestRandomRoomNumber(@RequestParam("uuid") String uuid) {
        return ResponseEntity.ok(this.mainService.requestRandomRoomNumber(uuid));
    }

    // SDP Offer와 Streaming 관련 메소드는 REST API에서도 유지할 수 있으나,
    // 이 부분은 실제 응답 구조나 클라이언트의 요구에 따라 달라질 수 있습니다.
    @GetMapping("/offer")
    public ResponseEntity<?> displaySampleSdpOffer() {
        // 예제 응답
        return new ResponseEntity<>("SDP Offer details here", HttpStatus.OK);
    }

    @GetMapping("/stream")
    public ResponseEntity<?> displaySampleStreaming() {
        // 예제 응답
        return new ResponseEntity<>("Streaming details here", HttpStatus.OK);
    }
}
