package module4.projectmd4.service.impl;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.model.dto.request.FormProduct;
import module4.projectmd4.model.entity.Category;
import module4.projectmd4.model.entity.Product;
import module4.projectmd4.repository.ICategoryRepository;
import module4.projectmd4.repository.IProductRepository;
import module4.projectmd4.service.IProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public Page<Product> findProductsByKeyword(String keyword, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findByKeyword(keyword, pageable);
    }

    @Override
    @Transactional
    public void add(FormProduct formProduct) {
        Category category = categoryRepository.findById(formProduct.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        boolean isSaved = false;
        while (!isSaved) {
            String sku = UUID.randomUUID().toString();
            Product product = Product.builder()
                    .sku(sku)
                    .productName(formProduct.getProductName())
                    .description(formProduct.getDescription())
                    .unitPrice(formProduct.getUnitPrice())
                    .stockQuantity(formProduct.getStockQuantity())
                    .image(formProduct.getImage())
                    .category(category)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            try {
                productRepository.save(product);
                isSaved = true;
            } catch (DataIntegrityViolationException e) {
                System.out.println("SKU conflict detected, generating a new SKU and retrying...");
            }
        }
    }
}
