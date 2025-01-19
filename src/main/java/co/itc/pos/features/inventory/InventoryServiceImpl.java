package co.itc.pos.features.inventory;

import co.itc.pos.domain.Inventory;
import co.itc.pos.domain.Product;
import co.itc.pos.features.Product.ProductRepository;
import co.itc.pos.features.inventory.dto.InventoryRequest;
import co.itc.pos.features.inventory.dto.InventoryResponse;
import co.itc.pos.mapper.InventoryMapper;
import co.itc.pos.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductRepository productRepository;



    @Override
    public CustomPage<InventoryResponse> getInventories(int page, int size) {

        Page<Inventory> inventories = inventoryRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));

        return customPage(inventories.map(inventoryMapper::toInventoryResponse));
    }

    @Override
    public InventoryResponse getInventory(String uuid) {
        return null;
    }

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {

        Inventory inventory = inventoryMapper.toInventory(request);
        Product product = productRepository.findByName(request.productName());

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        inventory.setQty(inventory.getQty());
        inventory.setProduct(product);
        inventory.setUuid(UUID.randomUUID().toString());
        inventory.setStatus("CREAT_STOCK");
        inventoryRepository.save(inventory);

        return inventoryMapper.toInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse stockIn(String uuid, InventoryRequest request) {
        Inventory inventory = inventoryRepository.findByUuid(uuid);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found");
        }

        inventory.setQty(inventory.getQty() + request.qty());
        inventory.setStatus("STOCK_IN");
        inventoryRepository.save(inventory);

        return inventoryMapper.toInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse withdraw(String uuid, InventoryRequest request) {
        Inventory inventory = inventoryRepository.findByUuid(uuid);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found");
        }

        if (inventory.getQty() < request.qty()) {
            throw new RuntimeException("Insufficient inventory quantity");
        }

        inventory.setQty(inventory.getQty() - request.qty());
        inventory.setStatus("WITHDRAW_STOCK");
        inventoryRepository.save(inventory);

        return inventoryMapper.toInventoryResponse(inventory);
    }

    @Override
    public void deleteInventory(String uuid) {

        Inventory inventory = inventoryRepository.findByUuid(uuid);

        inventoryRepository.delete(inventory);

    }

    @Override
    public InventoryResponse disableInventory(String uuid) {

        Inventory inventory = inventoryRepository.findByUuid(uuid);

        if (inventory == null) {
            throw new RuntimeException("Inventory with uuid: " + uuid + " not found");
        }

        inventory.setActive(false);

        inventoryRepository.save(inventory);
        return inventoryMapper.toInventoryResponse(inventory);

    }

    @Override
    public InventoryResponse enableInventory(String uuid) {

        Inventory inventory = inventoryRepository.findByUuid(uuid);

        if (inventory == null) {
            throw new RuntimeException("Inventory with uuid: " + uuid + " not found");
        }

        inventory.setActive(true);

        inventoryRepository.save(inventory);

        return inventoryMapper.toInventoryResponse(inventory);
    }

    public CustomPage<InventoryResponse> customPage(Page<InventoryResponse> page){

        CustomPage<InventoryResponse> customPage = new CustomPage<>();

        //check if page has next
        if(page != null && page.hasPrevious()){
            customPage.setPrevious(true); // Set to true if there is a previous page
        } else {
            customPage.setPrevious(false); // Set to false if there is no previous page
        }

        if(page != null && page.hasNext()){
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
