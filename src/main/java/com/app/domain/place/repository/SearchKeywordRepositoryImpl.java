package com.app.domain.place.repository;

import com.app.domain.place.entity.SearchKeyword;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SearchKeywordRepositoryImpl implements SearchKeywordRepository {
    private final SearchKeywordJpaRepository searchKeywordJpaRepository;

    @Override
    public synchronized SearchKeyword save(SearchKeyword searchKeyword){
        searchKeywordJpaRepository.save(searchKeyword);
        return searchKeyword;
    }

    @Override
    @Transactional
    public SearchKeyword updateSearchCount(SearchKeyword searchKeyword) {
        searchKeywordJpaRepository.findBySearchKeywordId(searchKeyword.getSearchKeywordId())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_SEARCH_KEYWORD));
        searchKeyword.updateSearchCountOne();
        return searchKeyword;
    }

    @Override
    public Optional<SearchKeyword> findByKeyword(String keyword) {
        return searchKeywordJpaRepository.findByKeyword(keyword);
    }





}
