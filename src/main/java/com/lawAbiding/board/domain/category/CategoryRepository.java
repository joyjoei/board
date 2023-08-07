package com.lawAbiding.board.domain.category;

import com.lawAbiding.board.domain.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    /*@Query(value = "SELECT GUBUN AS CODE, GUBUN_NAME AS VALUE FROM TB_LAW_COMMONCODE", nativeQuery = true)
    List<CategoryEntity> findByCategorySelect();*/
}

