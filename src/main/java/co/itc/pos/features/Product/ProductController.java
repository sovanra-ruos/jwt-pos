package co.itc.pos.features.Product;

import co.itc.pos.features.Product.dto.ProductRequest;
import co.itc.pos.features.Product.dto.ProductResponse;
import co.itc.pos.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<CustomPage<ProductResponse>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size){
        CustomPage<ProductResponse> postResponseCustomPage = productService.getProducts(page, size);
        return ResponseEntity.ok(postResponseCustomPage);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete product by uuid")
    public ResponseEntity<Void> deleteProduct(@PathVariable String uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable/{uuid}")
    @Operation(summary = "Disable product by uuid")
    public ResponseEntity<ProductResponse> disableProduct(@PathVariable String uuid) {
        return ResponseEntity.ok(productService.disableProduct(uuid));
    }

    @PutMapping("/enable/{uuid}")
    @Operation(summary = "Enable product by uuid")
    public ResponseEntity<ProductResponse> enableProduct(@PathVariable String uuid) {
        return ResponseEntity.ok(productService.enableProduct(uuid));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update product by uuid")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String uuid, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(uuid, productRequest));
    }

}
