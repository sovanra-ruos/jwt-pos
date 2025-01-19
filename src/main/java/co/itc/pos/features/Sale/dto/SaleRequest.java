package co.itc.pos.features.Sale.dto;

import lombok.Builder;

@Builder
public record SaleRequest(
        String saleItemUuid,
        double total,
        double paid,
        double change,
        boolean active,
        String paymentMethod,
        float discount

) {
}
