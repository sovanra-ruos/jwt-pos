package co.itc.pos.features.Sale;

import co.itc.pos.domain.*;
import co.itc.pos.features.Product.ProductRepository;
import co.itc.pos.features.Sale.dto.SaleRequest;
import co.itc.pos.features.Sale.dto.SaleResponse;
import co.itc.pos.features.SaleItem.ItemProductRepository;
import co.itc.pos.features.SaleItem.SaleItemRepository;
import co.itc.pos.features.inventory.InventoryRepository;
import co.itc.pos.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final SaleItemRepository saleItemRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final ItemProductRepository itemProductRepository;

    @Override
    public SaleResponse createSale(SaleRequest saleRequest) {

        Sale sale = saleMapper.toSale(saleRequest);

        SaleItem saleItem = saleItemRepository.findByUuid(saleRequest.saleItemUuid());

        if (saleItem == null) {
            throw new RuntimeException("SaleItem not found");
        }

        ItemProduct itemProduct = itemProductRepository.findBySaleItem(saleItem);

        if (itemProduct == null) {
            throw new RuntimeException("ItemProduct not found");
        }

        System.out.println("ItemProduct: " + itemProduct.getQty());
        Inventory inventory = inventoryRepository.findInventoryByProduct(itemProduct.getProduct());

        if (inventory == null) {
            throw new RuntimeException("Inventory not found");
        }

        if (inventory.getQty() == 0) {
            throw new RuntimeException("Product is out of stock");
        }

        if (inventory.getQty() > 0 && inventory.getQty() >= itemProduct.getQty()) {

            if (inventory.getQty() == itemProduct.getQty()) {
                inventory.setQty(0);
            } else {
                inventory.setQty(inventory.getQty() - itemProduct.getQty());
            }

            sale.setSaleItem(saleItem);
            sale.setUuid(UUID.randomUUID().toString());
            sale.setActive(true);

            saleRepository.save(sale);

            return saleMapper.toSaleResponse(sale);
        } else {
            throw new RuntimeException("Insufficient inventory quantity");
        }
    }
}
