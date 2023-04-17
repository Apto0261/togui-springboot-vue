package com.project.togui.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
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
            boardDto.add(BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createDate(board.getCreatedDate())
                .nickname(board.getMember().getName())
                .viewCnt(board.getViewCnt())
                .build());
        }

        return boardDto;
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
