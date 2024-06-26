package meoipzi.meoipzi.outfit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meoipzi.meoipzi.outfit.domain.Outfit;

@Getter
@Setter
@NoArgsConstructor
public class OutfitTotalResponseDTO {
    //검색하기 창은 ...client
    //private Long id;
    private String imgUrl;
    private Long outfitId;

    public OutfitTotalResponseDTO(Outfit outfit) {
        this.outfitId = outfit.getId();
        this.imgUrl = outfit.getImgUrl();
    }


}
