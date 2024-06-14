package module4.projectmd4.service;

import module4.projectmd4.model.dto.request.FormCategory;
import module4.projectmd4.model.dto.request.FormProduct;
import module4.projectmd4.model.entity.Category;
import module4.projectmd4.model.entity.Product;
import org.springframework.data.domain.Page;

public interface IProductService {
    Page<Product> findProductsByKeyword(String keyword, int page, int size, String sortBy, String sortDirection);

    void add(FormProduct formProduct);
}
