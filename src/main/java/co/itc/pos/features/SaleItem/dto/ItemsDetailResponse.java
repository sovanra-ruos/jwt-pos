package co.itc.pos.features.SaleItem.dto;

import co.itc.pos.features.Product.dto.ProductResponse;
import lombok.Builder;

@Builder
public record ItemsDetailResponse(
        ProductResponse product,
        int qty
) {
}
