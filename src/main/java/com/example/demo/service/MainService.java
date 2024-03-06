package com.example.demo.service;

import com.example.demo.domain.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MainService {
    // 메인 페이지에서 필요한 데이터 반환
    List<Room> displayMainPageData(Long id, String uuid);

    // 방 선택 또는 생성 처리 후 해당 방의 정보 반환
    ResponseEntity<Room> processRoomSelection(String s, String sid, String uuid);

    // 선택된 방의 정보 반환
    Room displaySelectedRoom(String sid, String uuid);

    // 방 나가기 처리. 성공 여부를 boolean 또는 ResponseEntity로 반환할 수 있음
    boolean processRoomExit(String sid, String uuid);

    // 랜덤 방 번호 생성 및 반환
    Long requestRandomRoomNumber(String uuid);

    Object displayMainPage(Long id, String uuid);
}
