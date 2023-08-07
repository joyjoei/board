package com.lawAbiding.board.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, String> {

    List<FileEntity> findAllByBOARDSEQ(int boardSeq);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TB_LAW_FILE e WHERE e.FILE_SEQ = :fileSeq" , nativeQuery = true)
    int deleteByFileSeq(@Param("fileSeq") int fileSeq);
}

