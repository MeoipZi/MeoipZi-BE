package meoipzi.meoipzi.community.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import meoipzi.meoipzi.community.domain.Community;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
/* 커뮤니티 글 목록 조회 시 반환할 DTO */
public class CommunityListResponseDTO {
    private Long communityId;
    private String title; // 제목
    private String imgUrl; // 첨부 이미지 파일
    private LocalDateTime createdAt; // 글 작성 일자
    private int commentsCount; // 댓글 갯수
    private int likesCount; // 하트 개수
    private String formattedCreatedAt; // 'n일 전' 형식
    private String category; // 카테고리

    public CommunityListResponseDTO(Community community){
        this.communityId = community.getId();
        this.category = community.getCategory();
        this.likesCount = community.getLikesCount();
        this.commentsCount = community.getCommentsCount();
        this.title = community.getTitle();

        List<String> images = community.getImgUrl();
        if (images != null && !images.isEmpty()) {
            this.imgUrl = images.get(0); // 게시글의 사진 리스트 중 첫번째 사진 파일 반환
        } else {
            this.imgUrl = null; // 이미지가 없는 경우 null 설정
        }

        this.createdAt = community.getCreatedAt();
        this.formattedCreatedAt = calculateTimeAgo(createdAt);
    }


    // 문자열 계산 메서드
    private String calculateTimeAgo(LocalDateTime createdAt){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createdAt ,now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();
        long weeks = days / 7;
        long months = days / 30;

        if(minutes < 1) {
            return "방금 전";
        } else if(hours < 1) {
            return minutes + "분 전";
        } else if (days < 1) {
            return hours + "시간 전";
        } else if(days < 7) {
            return days + "일 전";
        } else if (weeks < 4) {
            return weeks + "주 전";
        } else {
            return months + "개월 전";
        }
    }
}