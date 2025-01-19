package co.itc.pos.features.inventory.dto;

import co.itc.pos.features.Product.dto.ProductResponse;
import lombok.Builder;

@Builder
public record InventoryResponse(
        String uuid,
        int qty,
        ProductResponse product
) {
}
