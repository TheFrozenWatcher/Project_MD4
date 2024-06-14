package module4.projectmd4.repository;

import module4.projectmd4.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :name")
    Optional<User> findByUsername(String name);

    @Query("select count(u) = 0 from User u where u.username = :name")
    boolean uniqueUsername(String name);
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("select count(u) = 0 from User u where u.email = :email")
    boolean uniqueEmail(String email);

    @Query("select count(u) = 0 from User u where u.phone = :phone")
    boolean uniquePhoneNumber(String phone);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.fullname) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<User> findByKeyword(String keyword, Pageable pageable);
}
