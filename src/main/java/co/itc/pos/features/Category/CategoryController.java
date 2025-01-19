package co.itc.pos.features.Category;

import co.itc.pos.features.Category.dto.CategoryRequest;
import co.itc.pos.features.Category.dto.CategoryResponse;
import co.itc.pos.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<CustomPage<CategoryResponse>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size){
        CustomPage<CategoryResponse> postResponseCustomPage = categoryService.findAll(page, size);
        return ResponseEntity.ok(postResponseCustomPage);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get category by uuid")
    public ResponseEntity<CategoryResponse> getCategoryByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(categoryService.getCategoryByUuid(uuid));
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update category by uuid")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String uuid, @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.updateCategory(uuid, categoryRequest));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete category by uuid")
    public ResponseEntity<Void> deleteCategory(@PathVariable String uuid) {
        categoryService.deleteCategory(uuid);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/disable/{uuid}")
    @Operation(summary = "Disable category by uuid")
    public ResponseEntity<CategoryResponse> disableCategory(@PathVariable String uuid) {
        return ResponseEntity.ok(categoryService.disableCategory(uuid));
    }

    @PutMapping("/enable/{uuid}")
    @Operation(summary = "Enable category by uuid")
    public ResponseEntity<CategoryResponse> enableCategory(@PathVariable String uuid) {
        return ResponseEntity.ok(categoryService.enableCategory(uuid));
    }

}
