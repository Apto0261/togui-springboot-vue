package com.project.togui.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {

    private Long id;

    private String title;

    private String content;

    private String nickname;
    
    private LocalDateTime createDate;

    private int viewCnt;


}
