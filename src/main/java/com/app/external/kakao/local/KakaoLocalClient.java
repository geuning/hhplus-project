package com.app.external.kakao.local;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoLocalClient", url = "${kakao.local-domain}")
public interface KakaoLocalClient {

    @GetMapping(value = "/v2/local/search/keyword.json")
    KakaoSearchKeywordDto.Response searchPlaceWithKeyword(@RequestHeader("Authorization") String authorization,
                                                          @SpringQueryMap KakaoSearchKeywordDto.Request request);

}
