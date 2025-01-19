package co.itc.pos.features.SaleItem;

import co.itc.pos.domain.Inventory;
import co.itc.pos.domain.ItemProduct;
import co.itc.pos.domain.Product;
import co.itc.pos.domain.SaleItem;
import co.itc.pos.features.Product.ProductRepository;
import co.itc.pos.features.SaleItem.dto.SaleItemRequest;
import co.itc.pos.features.SaleItem.dto.SaleItemResponse;
import co.itc.pos.features.SaleItem.dto.UpdateQuantityRequest;
import co.itc.pos.features.inventory.InventoryRepository;
import co.itc.pos.mapper.SaleItemMapper;
import co.itc.pos.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;
    private final SaleItemMapper saleItemMapper;
    private final ProductRepository productRepository;
    private final ItemProductRepository itemProductRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public SaleItemResponse createSaleItem(SaleItemRequest saleItemRequest) {

        System.out.println("sale request"+saleItemRequest);

        SaleItem saleItem = saleItemMapper.toSaleItem(saleItemRequest);

        saleItem.setUuid(UUID.randomUUID().toString());

        List<ItemProduct> itemProducts = saleItemRequest.items().stream().map(productRequest -> {
            Product product = productRepository.findByName(productRequest.productName());

            Inventory inventory = inventoryRepository.findInventoryByProduct_Uuid(product.getUuid());

            if (inventory.getQty() > 0 && inventory.getQty() >= productRequest.qty()) {
                ItemProduct itemProduct = new ItemProduct();
                itemProduct.setProduct(product);
                itemProduct.setQty(productRequest.qty());
                itemProduct.setSaleItem(saleItem);
                return itemProduct; // Ensure the itemProduct is returned here
            } else {
                throw new RuntimeException("Insufficient inventory quantity");
            }
        }).collect(Collectors.toList());

        saleItem.setSaleItemProducts(itemProducts);

        saleItemRepository.save(saleItem);

        return saleItemMapper.toSaleItemResponse(saleItem);
    }

    @Override
    public CustomPage<SaleItemResponse> getSaleItems(int page, int size) {

        Page<SaleItem> items = saleItemRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));

        return customPage(items.map(saleItemMapper::toSaleItemResponse));
    }

    @Override
    public SaleItemResponse getSaleItem(String uuid) {

        SaleItem saleItem = saleItemRepository.findByUuid(uuid);

        if (saleItem == null) {
            throw new NoSuchElementException("Sale item not found");
        }

        return saleItemMapper.toSaleItemResponse(saleItem);

    }

    @Override
    public void deleteSaleItem(String uuid) {
        SaleItem saleItem = saleItemRepository.findByUuid(uuid);
        if (saleItem == null) {
            throw new NoSuchElementException("Sale item not found");
        }
        saleItemRepository.delete(saleItem);
    }

    @Override
    public SaleItemResponse updateQuantity(String uuid, UpdateQuantityRequest request) {

        SaleItem saleItem = saleItemRepository.findByUuid(uuid);

        if (saleItem == null) {
            throw new NoSuchElementException("Sale item not found");
        }

        ItemProduct itemProduct = itemProductRepository.findBySaleItem_Uuid(uuid);

        if (itemProduct == null) {
            throw new NoSuchElementException("Item product not found");
        }

        itemProduct.setQty(request.qty());

        itemProductRepository.save(itemProduct);

        return saleItemMapper.toSaleItemResponse(saleItem);

    }

    public CustomPage<SaleItemResponse> customPage(Page<SaleItemResponse> page) {

        CustomPage<SaleItemResponse> customPage = new CustomPage<>();

        //check if page has next
        if (page != null && page.hasPrevious()) {
            customPage.setPrevious(true); // Set to true if there is a previous page
        } else {
            customPage.setPrevious(false); // Set to false if there is no previous page
        }

        if (page != null && page.hasNext()) {
            customPage.setNext(true); // Set to true if there is a next page
        } else {
            customPage.setNext(false); // Set to false if there is no next page
        }

        //set total
        customPage.setTotal((int) page.getTotalElements());
        customPage.setTotalElements(page.getTotalElements());

        //set total pages
        customPage.setResults(page.getContent());

        return customPage;
    }
}