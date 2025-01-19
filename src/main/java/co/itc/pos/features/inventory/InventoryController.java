package co.itc.pos.features.inventory;

import co.itc.pos.features.inventory.dto.InventoryRequest;
import co.itc.pos.features.inventory.dto.InventoryResponse;
import co.itc.pos.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping
    @Operation(summary = "Get all inventory")
    public ResponseEntity<CustomPage<InventoryResponse>> getAllInventory(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size){
        CustomPage<InventoryResponse> postResponseCustomPage = inventoryService.getInventories(page, size);

        return ResponseEntity.ok(postResponseCustomPage);
    }

    @PostMapping
    @Operation(summary = "Create a new inventory")
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest inventoryRequest) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryRequest));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete inventory by uuid")
    public ResponseEntity<Void> deleteInventory(@PathVariable String uuid) {
        inventoryService.deleteInventory(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/stock-in/{uuid}")
    @Operation(summary = "Stock in inventory")
    public ResponseEntity<InventoryResponse> stockIn(@PathVariable String uuid, @RequestBody InventoryRequest inventoryRequest) {
        return ResponseEntity.ok(inventoryService.stockIn(uuid, inventoryRequest));
    }

    @PutMapping("/withdraw/{uuid}")
    @Operation(summary = "Withdraw inventory")
    public ResponseEntity<InventoryResponse> withdraw(@PathVariable String uuid, @RequestBody InventoryRequest inventoryRequest) {
        return ResponseEntity.ok(inventoryService.withdraw(uuid, inventoryRequest));
    }

    @PutMapping("/disable/{uuid}")
    @Operation(summary = "Disable inventory by uuid")
    public ResponseEntity<InventoryResponse> disableInventory(@PathVariable String uuid) {
        return ResponseEntity.ok(inventoryService.disableInventory(uuid));
    }

    @PutMapping("/enable/{uuid}")
    @Operation(summary = "Enable inventory by uuid")
    public ResponseEntity<InventoryResponse> enableInventory(@PathVariable String uuid) {
        return ResponseEntity.ok(inventoryService.enableInventory(uuid));
    }


}
