package co.itc.pos.features.Product;

import co.itc.pos.domain.Category;
import co.itc.pos.domain.Product;
import co.itc.pos.features.Category.CategoryRepository;
import co.itc.pos.features.Product.dto.ProductRequest;
import co.itc.pos.features.Product.dto.ProductResponse;
import co.itc.pos.mapper.ProductMapper;
import co.itc.pos.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public CustomPage<ProductResponse> getProducts(int page, int size) {

        Page<Product> products = productRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));

        return customPage(products.map(productMapper::toProductResponse));

    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productMapper.toProduct(productRequest);

        Category category = categoryRepository.findByName(productRequest.categoryName());

        product.setCategory(category);

        product.setUuid(UUID.randomUUID().toString());

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(String uuid, ProductRequest productRequest) {

        Product product = productRepository.findByUuid(uuid);

        Category category = categoryRepository.findByName(productRequest.categoryName());

        product.setCategory(category);
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setDescription(productRequest.description());
        product.setImage(productRequest.image());

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteProduct(String uuid) {

        Product product = productRepository.findByUuid(uuid);

        productRepository.delete(product);
    }

    @Override
    public ProductResponse disableProduct(String uuid) {

        Product product = productRepository.findByUuid(uuid);

        if (product == null) {
            throw new NoSuchElementException("Product with uuid: " + uuid + " not found");
        }

        product.setActive(false);

        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse enableProduct(String uuid) {

        Product product = productRepository.findByUuid(uuid);

        if (product == null) {
            throw new NoSuchElementException("Product with uuid: " + uuid + " not found");
        }

        product.setActive(true);

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    public CustomPage<ProductResponse> customPage(Page<ProductResponse> page){

        CustomPage<ProductResponse> customPage = new CustomPage<>();

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
