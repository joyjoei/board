package com.lawAbiding.board.domain.category;

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
@Entity(name = "TB_LAW_COMMONCODE")
public class CategoryEntity implements Serializable {
    @Id
    private String gubun;

    @Column(name = "gubun_name")
    private String gubunName;
    @Column(name = "board_id")
    private String boardId;

    @Transient
    private String CODE;

    @Transient
    private String VALUE;

}
