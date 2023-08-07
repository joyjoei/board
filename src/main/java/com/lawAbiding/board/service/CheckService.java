package com.lawAbiding.board.service;

import com.lawAbiding.board.domain.check.CheckEntity;
import com.lawAbiding.board.domain.check.CheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CheckService {

    private final CheckRepository checkRepository;

    public CheckEntity getCheckAuth(String sabun) {
        return checkRepository.findById(sabun)
                .orElse(
                        null    //테이블에 정보가 없는 경우에 null 리턴
                );
    }

}

