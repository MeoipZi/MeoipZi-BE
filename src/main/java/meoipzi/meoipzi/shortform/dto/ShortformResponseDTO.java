package meoipzi.meoipzi.shortform.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import meoipzi.meoipzi.shortform.domain.ShortForm;

@Getter
@Setter
@RequiredArgsConstructor
/* 숏폼 하나 상세 조회 시 반환할 DTO */
public class ShortformResponseDTO {
    private Long shorformId;
    private String imgUrl; // 숏폼
    private String title; // 제목
    private String contents; // 내용
    private int likesCount; // 좋아요 개수
    private int commentsCount; // 댓글 개수
    private boolean likeOrNot; //좋아요 여부

    public ShortformResponseDTO(ShortForm shortform){
        this.shorformId = shortform.getId();
        this.imgUrl = shortform.getImgUrl();
        this.title = shortform.getTitle();
        this.contents = shortform.getContents();
        this.likesCount = shortform.getLikesCount();
        this.commentsCount = shortform.getCommentsCount();
        this.likeOrNot = false;
    }
}