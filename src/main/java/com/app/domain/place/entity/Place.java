package com.app.domain.place.entity;

import com.app.domain.common.BaseEntity;
import com.app.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchKeywordId;

    @Column(unique = true)
    private String keyword;

    private Long searchCount;


}
