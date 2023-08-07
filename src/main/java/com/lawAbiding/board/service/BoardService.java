package com.lawAbiding.board.service;

import com.lawAbiding.board.domain.board.BoardEntity;
import com.lawAbiding.board.domain.board.BoardInterface;
import com.lawAbiding.board.domain.board.BoardRepository;
import com.lawAbiding.board.domain.board.BoardSpecification;
import com.lawAbiding.board.domain.category.CategoryEntity;
import com.lawAbiding.board.domain.category.CategoryRepository;
import com.lawAbiding.board.domain.file.FileRepository;
import com.lawAbiding.board.domain.file.FileEntity;
import com.lawAbiding.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final CategoryRepository categoryRepository;

    public BoardEntity boardCreate(BoardRequest boardRequest){
        BoardEntity entity = BoardEntity
                .builder()
                //.seqno(543)  // 시퀀스 설정
                .gubun(boardRequest.getGubun())
                .gubun_name(boardRequest.getGubun_name())
                .title(boardRequest.getTitle())
                .contents(boardRequest.getContents())
                .writer(boardRequest.getWriter())
               /* .ins_date(LocalDateTime.now())*/
                .ins_date(new Date())
                .ins_user_id(boardRequest.getIns_user_id())
                .ins_sabun(boardRequest.getIns_sabun())
               /* .file_path(boardRequest.getFile_path())
                .file_name(boardRequest.getFile_name())
                .file_path2(boardRequest.getFile_path2())
                .file_name2(boardRequest.getFile_name2())
                .file_path3(boardRequest.getFile_path3())
                .file_name3(boardRequest.getFile_name3())*/
                .build()
                ;

        return boardRepository.save(entity);
    };

    public List<BoardEntity> boardAllList(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "seqno"));
    };

    public List<BoardInterface> selectMainList() {
        return boardRepository.getTbLawBoardList();
    }

    public Page<BoardEntity> findAllByGubun(String gubun, Pageable pageable) {
        return boardRepository.findAllByGubun(gubun, pageable);
    }

    public Page<BoardEntity> findCategoryList(String gubun, String title, String contents, Pageable pageable) {

        Specification<BoardEntity> spec = (root, query, criteriaBuilder) -> null;

        if(!gubun.equals(""))
            spec = spec.and(BoardSpecification.equalGubun(gubun));
        if(!title.equals(""))
            spec = spec.and(BoardSpecification.likeTitle(title));
        if(!contents.equals(""))
            spec = spec.and(BoardSpecification.likeContents(contents));

        return boardRepository.findAll(spec, pageable);

    }

    public BoardEntity boardView(int seqno) {
        int viewCnt = boardRepository.modifyingPlusByViewCnt(seqno);
        //boardRepository.findById(seqno).get();
        return boardRepository.findById(seqno)
                .map( it -> {
                    return it;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : " + seqno) ;
                        }
                );
    }
    @Transactional
    public boolean deleteById(int seqno)  {
       /* boardRepository.findById(seqno)
                .map( it -> {
                    return it;
                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : " + seqno) ;
                        }
                );*/

        //게시글이 있으면 삭제
        int affectedRows = boardRepository.deleteEntityById(seqno);
        return affectedRows > 0;
    }

    /*public Page<BoardEntity> findAll(Pageable pageAble) {
        Page<BoardEntity> findAll = boardRepository.findAll(pageAble);
        System.out.println("페이지 를 알아보자 : " + findAll);
        for(BoardEntity boardEntity: findAll.getContent()){
            System.out.println("페이징: " + boardEntity.getSeqno());
        }
        return findAll;
    }*/

    public String findGubunName(String gubun) {
        return boardRepository.findGubunName(gubun);
    }

    public String findGubunEnName(String gubun) {
        return boardRepository.findGubunEnName(gubun);
    }

    public List<FileEntity> getBoardFileList(int boardSeq) {
       return fileRepository.findAllByBOARDSEQ(boardSeq);
    }

    public List<CategoryEntity> selectBoxBoard() {
        return categoryRepository.findAll();
    }

    public BoardEntity boardUpdate(BoardRequest boardRequest){
        int seqNo_ = boardRequest.getSeqNo();
        // 일단 idx에 맞는 값들을 찾아와
        Optional<BoardEntity> entity = this.boardRepository.findById(seqNo_);
        // ifPresent는 컨슈머를 매개변수로 입력받아서 객체가 존재할 때만 실행하는 Optional의 메소드입니다.
        entity.ifPresent(t ->{
            //엔티티의 객체를 바꿔준다.
            t.setContents(boardRequest.getContents());
            t.setTitle(boardRequest.getTitle());

            t.setGubun(boardRequest.getGubun());
            t.setGubun_name(boardRequest.getGubun_name());
            t.setModifier(boardRequest.getModifier());
            t.setMod_sabun(boardRequest.getMod_sabun());
            t.setMod_user_id(boardRequest.getMod_user_id());
            t.setMod_date(LocalDateTime.now());

            // 이걸 실행하면 idx 때문에 update가 실행됩니다.
            this.boardRepository.save(t);
        });

        return entity.get();
    }
}

