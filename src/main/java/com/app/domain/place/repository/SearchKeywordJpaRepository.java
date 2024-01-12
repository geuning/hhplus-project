package com.app.domain.place.repository;

import com.app.domain.place.entity.SearchKeyword;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SearchKeywordJpaRepository extends JpaRepository<SearchKeyword, Long> {

    Optional<SearchKeyword> findByKeyword(String keyword);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select sk from SearchKeyword sk where sk.searchKeywordId = :searchKeywordId")
    Optional<SearchKeyword> findBySearchKeywordId(@Param("searchKeywordId") Long searchKeywordId);
}
