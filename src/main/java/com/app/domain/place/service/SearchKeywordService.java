package com.app.domain.place.service;

import com.app.domain.place.entity.SearchKeyword;
import com.app.domain.place.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchKeywordService {

    private final SearchKeywordRepository searchKeywordRepository;

    @Transactional
    public SearchKeyword saveOrUpdateSearchCount(String keyword){
        Optional<SearchKeyword> optionalSearchKeyword = searchKeywordRepository.findByKeyword(keyword);

        //저장된 키워드 없을때
        if(optionalSearchKeyword.isEmpty()){
            SearchKeyword searchKeyword = SearchKeyword.create(keyword);
            return searchKeywordRepository.save(searchKeyword);
        }

        //저장된 키워드 있을때
        SearchKeyword searchKeyword = optionalSearchKeyword.get();
        searchKeywordRepository.updateSearchCount(searchKeyword);
        return searchKeyword;




    }

}
