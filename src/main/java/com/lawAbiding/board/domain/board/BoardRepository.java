package com.lawAbiding.board.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, JpaSpecificationExecutor<BoardEntity> { // 제네릭 타입: <엔티티 클래스, 엔티티클래스의 기본키>
    @Query(value = "SELECT T1.SEQNO, T1.GUBUN, T1.GUBUN_NAME, T1.BOARD_TP, T1.TITLE, T1.WRITER, T1.VIEW_CNT, T1.FILE_PATH, T1.FILE_NAME, T1.insDateFormat, T1.INS_DATE, T1.INS_USER_ID, T1.INS_SABUN " +
            " FROM (SELECT SEQNO, GUBUN, GUBUN_NAME, (SELECT BOARD_ID FROM TB_LAW_COMMONCODE A WHERE A.GUBUN = B.GUBUN) AS BOARD_TP, TITLE, WRITER, VIEW_CNT,  FILE_PATH, FILE_NAME, TO_CHAR(INS_DATE,'YYYY/MM/DD') AS insDateFormat, INS_DATE, INS_USER_ID, INS_SABUN  " +
            " FROM TB_LAW_BOARD B ORDER BY SEQNO DESC)T1  " +
            " WHERE ROWNUM  <= 15  ", nativeQuery = true )
    List<BoardInterface> getTbLawBoardList();


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE TB_LAW_BOARD m SET m.VIEW_CNT = m.VIEW_CNT+1 WHERE m.SEQNO = :seqno", nativeQuery = true)
    int modifyingPlusByViewCnt(int seqno);

    @Query(value = "SELECT GUBUN_NAME FROM TB_LAW_COMMONCODE  WHERE GUBUN = :gubun", nativeQuery = true )
    String findGubunName(String gubun);

    @Query(value = "SELECT BOARD_ID FROM TB_LAW_COMMONCODE  WHERE GUBUN = :gubun", nativeQuery = true )
    String findGubunEnName(String gubun);


    Page<BoardEntity> findAllByGubun(String gubun, Pageable pageable);

    @Modifying
    @Query("DELETE FROM TB_LAW_BOARD e WHERE e.seqno = :seqno")
    int deleteEntityById(@Param("seqno") int seqno);
}

