package com.app.domain.place.entity;

import com.app.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchKeyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchKeywordId;

    @Column(unique = true)
    private String keyword;

    private Long searchCount;

    @Builder
    public SearchKeyword(Long searchKeywordId, String keyword, Long searchCount){
        this.searchKeywordId = searchKeywordId;
        this.keyword = keyword;
        this.searchCount = searchCount;
    }


    public static SearchKeyword create(String keyword){
        return SearchKeyword.builder()
                .keyword(keyword)
                .searchCount(1L)
                .build();
    }




}
