package co.itc.pos.features.SaleItem;

import co.itc.pos.features.SaleItem.dto.SaleItemRequest;
import co.itc.pos.features.SaleItem.dto.SaleItemResponse;
import co.itc.pos.features.SaleItem.dto.UpdateQuantityRequest;
import co.itc.pos.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale-item")
public class SaleItemController {

    private final SaleItemService saleItemService;


    @GetMapping
    @Operation(summary = "Get all sale items")
    public ResponseEntity<CustomPage<SaleItemResponse>> getSaleItems(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        CustomPage<SaleItemResponse> postResponseCustomPage = saleItemService.getSaleItems(page, size);

        return ResponseEntity.ok(postResponseCustomPage);
    }


    @PostMapping
    @Operation(summary = "Create a sale item")
    public ResponseEntity<SaleItemResponse> createSaleItem(@RequestBody SaleItemRequest saleItemRequest) {
        return ResponseEntity.ok(saleItemService.createSaleItem(saleItemRequest));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get sale item by uuid")
    public ResponseEntity<SaleItemResponse> getSaleItem(@PathVariable String uuid) {
        return ResponseEntity.ok(saleItemService.getSaleItem(uuid));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete sale item by uuid")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable String uuid) {
        saleItemService.deleteSaleItem(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-quantity/{uuid}")
    @Operation(summary = "Update quantity of sale item by uuid")
    public ResponseEntity<SaleItemResponse> updateQuantity(@PathVariable String uuid, @RequestBody UpdateQuantityRequest request) {
        return ResponseEntity.ok(saleItemService.updateQuantity(uuid, request));
    }

    
}
