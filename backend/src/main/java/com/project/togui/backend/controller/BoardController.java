package com.project.togui.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.togui.backend.dto.BoardDto;
import com.project.togui.backend.security.CustomUserDetails;
import com.project.togui.backend.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> getBoardList(){
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> wirte(@RequestBody BoardDto boardDto,@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long boardId = boardService.write(boardDto,customUserDetails.getMember());
        return new ResponseEntity<>("Success id : " + boardId, HttpStatus.OK);
    }
}
