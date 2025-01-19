package co.itc.pos.features.Sale;

import co.itc.pos.features.Sale.dto.SaleRequest;
import co.itc.pos.features.Sale.dto.SaleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale")
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    @Operation(summary = "Create a sale")
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest saleRequest) {
        return ResponseEntity.ok(saleService.createSale(saleRequest));
    }

}
