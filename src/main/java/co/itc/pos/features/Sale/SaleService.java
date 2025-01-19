package co.itc.pos.features.Sale;

import co.itc.pos.features.Sale.dto.SaleRequest;
import co.itc.pos.features.Sale.dto.SaleResponse;

public interface SaleService {

    SaleResponse createSale(SaleRequest saleRequest);



}
