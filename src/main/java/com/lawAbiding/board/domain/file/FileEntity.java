package com.lawAbiding.board.domain.file;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "TB_LAW_FILE")
public class FileEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_LAW_FILE")
    @SequenceGenerator(sequenceName = "SEQ_TB_LAW_FILE", name = "SEQ_TB_LAW_FILE", allocationSize = 1)
    @Column(name = "FILE_SEQ")
    private int FILESEQ;

    @Column(name = "FILE_PATH")
    private String FILEPATH;

    @Column(name = "FILE_NAME")
    private String FILENAME;

    @Column(name = "REG_DATE")
    private LocalDateTime REGDATE;

    @Column(name = "BOARD_ID")
    private String BOARDID;  //게시판 이름

    @Column(name = "BOARD_SEQ")
    private int BOARDSEQ; //게시판 시퀀스 => 조회 기준


}
