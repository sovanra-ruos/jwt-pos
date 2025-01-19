package co.itc.pos.features.Sale.dto;

import co.itc.pos.features.SaleItem.dto.SaleItemResponse;
import lombok.Builder;

@Builder
public record SaleResponse(
        String uuid,
        SaleItemResponse items,
        double total,
        double paid,
        double change,
        boolean active,
        String paymentMethod,
        float discount
) {
}
