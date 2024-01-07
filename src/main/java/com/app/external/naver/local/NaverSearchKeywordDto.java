package com.app.external.naver.local;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class NaverSearchKeywordDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request{
        private String query;
        private int start;
        private int display;
        private String sort;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response{
        private LocalDateTime lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<Item> items;

        @Getter
        @Builder
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Item{
            private String title;
            private String link;
            private String category;
            private String description;
            private String telephone;
            private String address;
            private String roadAddress;
            private String mapx;
            private String mapy;
        }
    }
}
