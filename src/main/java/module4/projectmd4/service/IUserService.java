package module4.projectmd4.service;

import module4.projectmd4.model.entity.User;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<User> findUsersByKeyword(String keyword, int page, int size, String sortBy, String sortDirection);

    String block(Long userId);
}
