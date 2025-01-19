package co.itc.pos.features.inventory;

import co.itc.pos.features.inventory.dto.InventoryRequest;
import co.itc.pos.features.inventory.dto.InventoryResponse;
import co.itc.pos.utils.CustomPage;

public interface InventoryService {

    CustomPage<InventoryResponse> getInventories(int page, int size);

    InventoryResponse getInventory(String uuid);

    InventoryResponse createInventory(InventoryRequest request);

    InventoryResponse stockIn(String uuid, InventoryRequest request);

    InventoryResponse withdraw(String uuid, InventoryRequest request);

    void deleteInventory(String uuid);

    InventoryResponse disableInventory(String uuid);

    InventoryResponse enableInventory(String uuid);
}
