package com.project.togui.backend.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.togui.backend.entity.Board;

@Transactional
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
}
