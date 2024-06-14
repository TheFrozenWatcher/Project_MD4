package module4.projectmd4.repository;

import module4.projectmd4.model.entity.Category;
import module4.projectmd4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.productName = :name")
    boolean uniqueName(String name);
    @Query("select p from Product p where p.sku = :sku")
    boolean uniqueSku(String sku);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<Product> findByKeyword(String keyword, Pageable pageable);
}
