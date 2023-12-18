package com.app.external.naver.local;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${naver.local-domain}", name = "naverLocalClient")
public interface NaverLocalClient {

    @GetMapping(value = "/v1/search/local.json")
    NaverSearchKeywordDto.Response searchPlaceWithKeyword(@RequestHeader("X-Naver-Client-Id") String clientId,
                                                          @RequestHeader("X-Naver-Client-Secret") String secretId,
                                                          @SpringQueryMap NaverSearchKeywordDto.Request request
    );
}
