package co.itc.pos.mapper;

import co.itc.pos.domain.Inventory;
import co.itc.pos.features.inventory.dto.InventoryRequest;
import co.itc.pos.features.inventory.dto.InventoryResponse;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface InventoryMapper {

    InventoryResponse toInventoryResponse(Inventory inventory);

    Inventory toInventory(InventoryRequest inventoryRequest);


}

