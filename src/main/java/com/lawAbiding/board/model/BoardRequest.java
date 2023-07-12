package com.lawAbiding.board.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardRequest {
    @NotBlank
    private int seqno;
    @NotBlank
    private String gubun;
    @NotBlank
    private String gubun_name;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;

    private String writer;

    private Date ins_date;

    private String ins_user_id;

    private String ins_email;

    private String ins_sabun;

    private String file_path;

    private String file_name;

    private String file_path2;

    private String file_name2;

    private String file_path3;

    private String file_name3;

}
