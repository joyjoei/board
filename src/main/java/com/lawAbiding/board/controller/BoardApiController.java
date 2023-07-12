package com.lawAbiding.board.controller;

import com.lawAbiding.board.domain.BoardEntity;
import com.lawAbiding.board.model.BoardRequest;
import com.lawAbiding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;


    @PostMapping("")
    public BoardEntity create(@Valid @RequestBody BoardRequest boardRequest){
        return boardService.create(boardRequest);
    }
}
