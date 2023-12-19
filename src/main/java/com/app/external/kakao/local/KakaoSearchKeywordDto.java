package com.app.external.kakao.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

public class KakaoSearchKeywordDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {
        private String query;
        private int page;
        private int size;
        private String sort;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response{
        private List<Document> documents;
        private Meta meta;

        @Getter
        @Builder
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Document{
            private String addressName;
            private String categoryGroupCode;
            private String categoryGroupName;
            private String categoryName;
            private String distance;
            private String id;
            private String phone;
            private String placeName;
            private String placeUrl;
            private String roadAddressName;
            private String x;
            private String y;
        }

        @Getter
        @Builder
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Meta{
            private Boolean isEnd;
            private Long pageableCount;
            private SameName sameName;

            @Getter
            @Builder
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
            public static class SameName{
                private String keyword;
                private String[] region;
                private String selectedRegion;
            }
        }
    }
}
