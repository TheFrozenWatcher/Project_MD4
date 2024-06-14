package module4.projectmd4.repository;

import module4.projectmd4.model.entity.Category;
import module4.projectmd4.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.categoryName = :name")
    boolean uniqueName(String name);
    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Category> findByKeyword(String keyword, Pageable pageable);
}
