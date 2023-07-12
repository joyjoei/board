package com.lawAbiding.board.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "TB_LAW_BOARD")
@IdClass(BoardPk.class)
public class BoardEntity {
    @Id
    @Column(name = "seqno")
    private int seqno;

    @Id
    @Column(name = "gubun")
    private String gubun;

    private String gubun_name;

    private String title;

    @Column(name = "contents")
    @Lob
    private String contents;

    private String writer;

    private int view_cnt;

    private String file_path;

    private String file_name;

  //  @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ins_date;

    private String ins_user_id;

    private String ins_email;

    private String modifier;

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime mod_date;

    private String mod_user_id;

    private String ins_sabun;

    private String mod_sabun;

    private String mod_email;

    private String file_path2;
    private String file_name2;
    private String file_path3;
    private String file_name3;


}
