package com.project.togui.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.togui.backend.entity.Authority;
import com.project.togui.backend.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class SignResponse {

    private Long id;

    private String nickname;

    private String name;

    private String email;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.roles = member.getRoles();
    }
}
