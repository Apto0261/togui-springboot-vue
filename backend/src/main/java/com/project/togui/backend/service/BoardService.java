package com.project.togui.backend.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.project.togui.backend.dto.BoardDto;
import com.project.togui.backend.entity.Board;
import com.project.togui.backend.entity.Member;
import com.project.togui.backend.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> getBoardList(){
        List<Board> boardlist = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "Id"));
        List<BoardDto> boardDto = new ArrayList<>();
        for (Board board : boardlist) {
            String createDateString = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            boardDto.add(BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createDate(createDateString)
                .nickname(board.getMember().getName())
                .viewCnt(board.getViewCnt())
                .build());
        }

        return boardDto;
    }

    public BoardDto getBoardDetail(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() ->
            new BadCredentialsException("삭제된 게시물 입니다."));
        
        String createDateString = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return BoardDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .createDate(createDateString)
            .nickname(board.getMember().getName())
            .viewCnt(board.getViewCnt())
            .build();
    }

    public Long write(BoardDto boardDto,Member member){
        Board board = Board.builder()
            .title(boardDto.getTitle())
            .content(boardDto.getContent())
            .member(member)
            .build();

        Long saveId = boardRepository.save(board).getId();

        return saveId;
    }
    
}
