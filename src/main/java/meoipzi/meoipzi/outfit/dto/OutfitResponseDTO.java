package meoipzi.meoipzi.outfit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meoipzi.meoipzi.outfit.domain.Outfit;
import meoipzi.meoipzi.product.dto.ProductListResponseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OutfitResponseDTO {
    //outfit id 에 맞는 3개의 상품 반환
    //코디
    private String imgUrl;
    private String content;
    private boolean likeOrNot;
    private boolean scrapOrNot;

    //상품
    private List<ProductListResponseDTO> productListResponseDTOS;

    public OutfitResponseDTO(Outfit outfit){
        this.imgUrl = outfit.getImgUrl();
        this.content = outfit.getContent();
        this.likeOrNot = false;
        this.scrapOrNot = false;
    }

    public void setProducts(List<ProductListResponseDTO> productListResponseDTOS){
        this.productListResponseDTOS = productListResponseDTOS;
    }

}
