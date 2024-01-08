package com.app.domain.place.repository;

import com.app.domain.place.entity.SearchKeyword;

import java.util.Optional;

public interface SearchKeywordRepository {
    Optional<SearchKeyword> findByKeyword(String keyword);

    SearchKeyword save(SearchKeyword searchKeyword);

    SearchKeyword updateSearchCount(SearchKeyword searchKeyword);



}
