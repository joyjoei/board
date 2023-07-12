package com.lawAbiding.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPk implements Serializable {

    private String gubun;
    private int seqno;
}
