package com.lawAbiding.board.domain.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "TB_LAW_BOARD")
//@IdClass(BoardPk.class)
public class BoardEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_LAW_BOARD")
    @SequenceGenerator(sequenceName = "SEQ_TB_LAW_BOARD", name = "SEQ_TB_LAW_BOARD", allocationSize = 1)
    @Column(name = "seqno")
    private int seqno;

    private String gubun;
    private String gubun_name;
    private String title;

    @Column(name = "contents")
    @Lob
    private String contents;
    private String writer;
    private int view_cnt;

    @Temporal(TemporalType.DATE)
    private Date ins_date;
    private String ins_sabun;
    private String ins_user_id;
    private String modifier;
    private String mod_user_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "Asia/Seoul")
    private LocalDateTime mod_date;
    private String mod_sabun;

    /*private String file_path;
    private String file_name;
    private String file_path2;
    private String file_name2;
    private String file_path3;
    private String file_name3;*/

    @Transient
    private String board_tp;

    @Transient
    private String board_id;

    @Transient
    private String insDateFormat;

}
