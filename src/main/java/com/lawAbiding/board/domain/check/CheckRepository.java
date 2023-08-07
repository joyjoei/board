package com.lawAbiding.board.domain.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface CheckRepository extends JpaRepository<CheckEntity, String> {
     /* default CheckEntity findById(String sabun) {
        return findById(sabun)
                .orElse(null)
    }
      return findById(sabun)
                .orElseThrow(
                        () -> BusinessException.from
                )

    };*/

}

