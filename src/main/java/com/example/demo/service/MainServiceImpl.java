package com.example.demo.service;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomService;
import com.example.demo.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MainServiceImpl implements MainService {
    private final RoomService roomService;
    private final Parser parser;

    @Autowired
    public MainServiceImpl(final RoomService roomService, final Parser parser) {
        this.roomService = roomService;
        this.parser = parser;
    }

    @Override
    public List<Room> displayMainPageData(Long id, String uuid) {
        // 메인 페이지에서 필요한 방 목록을 반환
        return new ArrayList<>(roomService.getRooms());
    }

    @Override
    public ResponseEntity<Room> processRoomSelection(String s, String sid, String uuid) {
        // 방 선택 또는 생성 로직 구현
        Optional<Long> optionalId = parser.parseId(sid);
        if (!optionalId.isPresent()) {
            return ResponseEntity.badRequest().build(); // ID 파싱 실패
        }

        Long id = optionalId.get();
        Optional<Room> room = roomService.findRoomByStringId(sid);
        if (!room.isPresent()) {
            // 방을 찾지 못한 경우, 새 방을 생성하거나 다른 로직을 수행
            Room newRoom = new Room(id); // 예시: 새 방 생성
            boolean added = roomService.addRoom(newRoom);
            if (added) {
                return ResponseEntity.ok(newRoom); // 새 방 생성 성공
            } else {
                return ResponseEntity.notFound().build(); // 방 생성 실패 또는 이미 존재하는 방
            }
        } else {
            // 방을 성공적으로 찾은 경우
            return ResponseEntity.ok(room.get());
        }
    }


    @Override
    public Room displaySelectedRoom(String sid, String uuid) {
        // 선택된 방 정보 반환 로직 구현...
        return roomService.findRoomByStringId(sid).orElse(null);
    }

    @Override
    public boolean processRoomExit(String sid, String uuid) {
        // 방 나가기 처리 로직 구현...
        return true; // 예시
    }

    @Override
    public Long requestRandomRoomNumber(String uuid) {
        // 랜덤 방 번호 생성 및 반환
        return randomValue();
    }

    @Override
    public Object displayMainPage(Long id, String uuid) {
        return null;
    }

    private Long randomValue() {
        return ThreadLocalRandom.current().nextLong(1, 100);
    }
}
