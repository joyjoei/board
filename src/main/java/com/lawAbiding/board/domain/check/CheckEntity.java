package com.lawAbiding.board.domain.check;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "TB_LAW_AUTH")
public class CheckEntity implements Serializable {
    @Id
    private String sabun;

    private String user_id;

    private String user_nm;

    private String dept_id; //부서

    private String dept_nm;

    private String user_mail;

    private String auth_type;


}
