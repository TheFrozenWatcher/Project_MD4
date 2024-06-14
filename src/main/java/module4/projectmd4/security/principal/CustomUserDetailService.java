package module4.projectmd4.security.principal;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.model.entity.User;
import module4.projectmd4.repository.IUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
    public class CustomUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.findByEmail(username);
        if (optionalUsers.isPresent()) {
            User user = optionalUsers.get();
            return CustomUserDetail.builder()
                    .id(user.getUserId())
                    .username(user.getUsername())
                    .fullName(user.getFullname())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .status(user.getStatus())
                    .authorities(
                            user.getRoles().stream()
                                    .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                                    .toList()
                    )
                    .build();
        }
        throw new UsernameNotFoundException("Username not found!");    }
}
