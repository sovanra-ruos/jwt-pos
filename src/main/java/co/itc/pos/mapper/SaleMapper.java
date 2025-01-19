package co.itc.pos.mapper;

import co.itc.pos.domain.Sale;
import co.itc.pos.features.Sale.dto.SaleRequest;
import co.itc.pos.features.Sale.dto.SaleResponse;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface SaleMapper {

    SaleResponse toSaleResponse(Sale sale);

    Sale toSale(SaleRequest saleRequest);
}
