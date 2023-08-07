package com.lawAbiding.board.service;

import com.lawAbiding.board.domain.board.BoardEntity;
import com.lawAbiding.board.domain.board.BoardInterface;
import com.lawAbiding.board.domain.compliance.ComplianceEntity;
import com.lawAbiding.board.domain.compliance.ComplianceRepository;
import com.lawAbiding.board.model.ComplianceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ComplianceService {

    private final ComplianceRepository complianceRepository;

    public ComplianceEntity selectAll(int seqno) {
        return complianceRepository.findById(seqno)
                .orElse(
                        null    //테이블에 정보가 없는 경우에 null 리턴
                );
    }

    public ComplianceEntity complianceUpdate(ComplianceRequest complianceRequest) {
        int seqNo_ = complianceRequest.getSeqNo();
        // 일단 idx에 맞는 값들을 찾아와
        Optional<ComplianceEntity> entity = this.complianceRepository.findById(seqNo_);

        entity.ifPresent(t ->{
            //엔티티의 객체를 바꿔준다.
            t.setContents(complianceRequest.getContents());
            t.setModifier(complianceRequest.getModifier());
            t.setMod_sabun(complianceRequest.getMod_sabun());
            t.setMod_user_id(complianceRequest.getMod_user_id());
            t.setMod_date(LocalDateTime.now());

            // 이걸 실행하면 idx 때문에 update가 실행됩니다.
            this.complianceRepository.save(t);
        });

        return entity.get();
    }
}

