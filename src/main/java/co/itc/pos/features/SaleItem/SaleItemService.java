package co.itc.pos.features.SaleItem;

import co.itc.pos.features.SaleItem.dto.SaleItemRequest;
import co.itc.pos.features.SaleItem.dto.SaleItemResponse;
import co.itc.pos.features.SaleItem.dto.UpdateQuantityRequest;
import co.itc.pos.utils.CustomPage;

public interface SaleItemService {

    SaleItemResponse createSaleItem(SaleItemRequest saleItemRequest);

    CustomPage<SaleItemResponse> getSaleItems(int page, int size);

    SaleItemResponse getSaleItem(String uuid);

    void deleteSaleItem(String uuid);

    SaleItemResponse updateQuantity(String uuid, UpdateQuantityRequest request);

}
