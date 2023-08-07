package com.lawAbiding.board.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
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
public class ComplianceRequest {

    private int seqNo;

    @NotBlank
    private String contents;

    private String writer;

    private Date ins_date;

    private String ins_user_id;

    private String ins_sabun;

    private String modifier;

    private Date mod_date;

    private String mod_user_id;

    private String mod_sabun;
}
