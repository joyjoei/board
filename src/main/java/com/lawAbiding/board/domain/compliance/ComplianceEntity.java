package com.lawAbiding.board.domain.compliance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
@Entity(name = "TB_LAW_COMPLIANCE")
public class ComplianceEntity implements Serializable {

    @Id
    private int seqno;

    @Lob
    private String contents;

    private String writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime ins_date;

    private String ins_user_id;

    private String ins_sabun;

    private String modifier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime mod_date;

    private String mod_user_id;

    private String mod_sabun;


}
