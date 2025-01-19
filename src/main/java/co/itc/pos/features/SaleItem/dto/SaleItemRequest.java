package co.itc.pos.features.SaleItem.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SaleItemRequest(
       List<AddToCartRequest> items
) {
}
