package MeoipZi.meoipzi.controller;

import MeoipZi.meoipzi.config.S3Config;
import MeoipZi.meoipzi.domain.Community;
import MeoipZi.meoipzi.domain.Shortform;
import MeoipZi.meoipzi.dto.CommunityDto.CommunityListResponseDTO;
import MeoipZi.meoipzi.dto.CommunityDto.CommunityRequestDTO;
import MeoipZi.meoipzi.dto.CommunityDto.CommunityResponseDTO;
import MeoipZi.meoipzi.dto.CommunityDto.CommunityUpdateRequestDTO;
import MeoipZi.meoipzi.dto.ShortformDto.ShortformListResponseDTO;
import MeoipZi.meoipzi.dto.ShortformDto.ShortformRequestDTO;
import MeoipZi.meoipzi.dto.ShortformDto.ShortformResponseDTO;
import MeoipZi.meoipzi.dto.ShortformDto.ShortformUpdateRequestDTO;
import MeoipZi.meoipzi.repository.CommunityRepository;
import MeoipZi.meoipzi.repository.ShortformRepository;
import MeoipZi.meoipzi.service.CommunityService;
import MeoipZi.meoipzi.service.ShortformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shortforms")
public class ShortformController {
    private ShortformRepository shortformRepository;
    private final ShortformService shortformService;
    private final S3Config s3Config;

    /* 숏폼 게시글 리스트 보기 */
    @GetMapping("")
    public ResponseEntity<List<ShortformListResponseDTO>> getCommunityList(){
        try {
            List<ShortformListResponseDTO> shortformListResponseDTOS = shortformService.getShortformList();

            return new ResponseEntity<>(shortformListResponseDTOS, HttpStatus.OK); // 조회된 커뮤니티 글 리스트 형식 반환
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 커뮤니티 게시글 하나 상세 조회 */
    @GetMapping("/{shortformId}")
    public ResponseEntity<?> getOneCommunity(@PathVariable Long shortformId) {
        try {
            ShortformResponseDTO shortformResponseDTO = shortformService.viewShortform(shortformId);
            return new ResponseEntity<>(shortformResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>("알 수 없는 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 숏폼 글 등록 */
    @PostMapping("")
    public ResponseEntity<?> createPost(ShortformRequestDTO shortformRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()) {
            try {
                shortformService.saveShortform(shortformRequestDTO);
                return new ResponseEntity<>("숏폼 등록에 성공하였습니다.", HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity<>("숏폼 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else return new ResponseEntity<>("접근 권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

    /* 숏폼 글 삭제 */
    @DeleteMapping("/{shortformId}")
    public ResponseEntity<?> deletePost(@PathVariable Long shortformId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            try {
                shortformService.deleteShortform(shortformId);
                return new ResponseEntity<>("숏폼이 삭제에 성공하였습니다.", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("숏폼 삭제 중에 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else return new ResponseEntity<>("접근 권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

/*
*  *//* 숏폼 글 수정 *//*
    @PatchMapping("/{shortformId}")
    public ResponseEntity<String> updatePost(@PathVariable Long shortformId, ShortformUpdateRequestDTO shortformUpdateRequestDTO) {
        try {
           shortformService.updateShortform(shortformId, shortformUpdateRequestDTO);
            return ResponseEntity.ok("게시글을 수정했습니다.");
        } catch (Exception e) {
            return ResponseEntity.ofNullable("게시글 수정에 실패하였습니다.");
        }
    }
    */

}

