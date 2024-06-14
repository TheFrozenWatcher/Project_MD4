package module4.projectmd4.service.impl;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.constant.RoleName;
import module4.projectmd4.model.entity.User;
import module4.projectmd4.repository.IUserRepository;
import module4.projectmd4.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    @Override
    public Page<User> findUsersByKeyword(String keyword, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findByKeyword(keyword, pageable);
    }

    @Override
    @Transactional
    public String block(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == RoleName.ROLE_ADMIN);

        if (isAdmin) {
            throw new IllegalArgumentException("Cannot block/unblock a user with admin role");
        }

        boolean newStatus = !user.getStatus();
        user.setStatus(newStatus);
        userRepository.save(user);

        return newStatus ? "unblocked" : "blocked";
    }
}
