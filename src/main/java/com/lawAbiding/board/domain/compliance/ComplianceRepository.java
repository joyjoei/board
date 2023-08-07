package com.lawAbiding.board.domain.compliance;

import com.lawAbiding.board.domain.board.BoardInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComplianceRepository extends JpaRepository<ComplianceEntity, Integer> { // 제네릭 타입: <엔티티 클래스, 엔티티클래스의 기본키>

}

