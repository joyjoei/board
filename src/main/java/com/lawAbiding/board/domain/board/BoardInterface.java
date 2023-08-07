package com.lawAbiding.board.domain.board;


import java.time.LocalDateTime;

public interface BoardInterface  {
    int getSeqno();

    String getTitle();

    LocalDateTime getIns_date();

    String getWriter();

    int getView_cnt();

    String getGubun();
    String getGubun_name();
    String getBoard_tp();
    String getInsDateFormat();



}
