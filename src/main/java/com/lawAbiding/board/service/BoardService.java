package com.lawAbiding.board.service;

import com.lawAbiding.board.domain.BoardEntity;
import com.lawAbiding.board.domain.BoardRepository;
import com.lawAbiding.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity create(BoardRequest boardRequest){
        BoardEntity entity = BoardEntity
                .builder()
                .seqno(543)  //TODO : 시퀀스 가져오기
                .gubun(boardRequest.getGubun())
                .gubun_name(boardRequest.getGubun_name())
                .title(boardRequest.getTitle())
                .contents(boardRequest.getContents())
                .writer(boardRequest.getWriter())
                .ins_date(LocalDateTime.now())
                .ins_user_id(boardRequest.getIns_user_id())
                .ins_email(boardRequest.getIns_email())
                .ins_sabun(boardRequest.getIns_sabun())
                .file_path(boardRequest.getFile_path())
                .file_name(boardRequest.getFile_name())
                .file_path2(boardRequest.getFile_path2())
                .file_name2(boardRequest.getFile_name2())
                .file_path3(boardRequest.getFile_path3())
                .file_name3(boardRequest.getFile_name3())
                .build()
                ;

        return boardRepository.save(entity);
    }
}
