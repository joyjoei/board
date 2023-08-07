package com.lawAbiding.board.domain.board;

import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {
    public static Specification<BoardEntity> equalGubun(String gubun) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("gubun"),gubun );
    }

    public static Specification<BoardEntity> likeTitle(String title) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("title"),"%" + title + "%");
    }


    public static Specification<BoardEntity> likeContents(String contents) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("contents"), "%" + contents + "%");
    }
}
