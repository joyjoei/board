package com.lawAbiding.board.service;

import com.lawAbiding.board.domain.file.FileEntity;
import com.lawAbiding.board.domain.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileEntity fileSave(FileEntity fileEntity){
        FileEntity entity = FileEntity
                .builder()
                .FILENAME(fileEntity.getFILENAME())
                .FILEPATH(fileEntity.getFILEPATH())
                .BOARDID(fileEntity.getBOARDID())
                .BOARDSEQ(fileEntity.getBOARDSEQ())
                .REGDATE(LocalDateTime.now())
                .build()
                ;

        return fileRepository.save(entity);
    };

    public Boolean deleteFileById(int fileSeq) {
        int result = fileRepository.deleteByFileSeq(fileSeq);
        System.out.println("delete result = "+ result);
        return result > 0;
    }
}

