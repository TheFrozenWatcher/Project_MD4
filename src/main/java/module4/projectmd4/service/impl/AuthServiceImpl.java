package module4.projectmd4.service.impl;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.constant.RoleName;
import module4.projectmd4.exception.CustomException;
import module4.projectmd4.model.dto.request.FormLogin;
import module4.projectmd4.model.dto.request.FormRegister;
import module4.projectmd4.model.dto.request.FormUpdateUser;
import module4.projectmd4.model.dto.response.JwtResponse;
import module4.projectmd4.model.entity.Role;
import module4.projectmd4.model.entity.User;
import module4.projectmd4.repository.IUserRepository;
import module4.projectmd4.security.jwt.JwtProvider;
import module4.projectmd4.security.principal.CustomUserDetail;
import module4.projectmd4.service.IAuthService;
import module4.projectmd4.service.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse handleLogin(FormLogin formLogin) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getEmail(), formLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email or password", HttpStatus.CONFLICT);
        }
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        String accessToken = jwtProvider.generateToken(userDetail);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .fullName(userDetail.getFullName())
                .username(userDetail.getUsername())
                .email(userDetail.getEmail())
                .address(userDetail.getAddress())
                .phone(userDetail.getPhone())
                .status(userDetail.getStatus())
                .roles(userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void handleRegister(FormRegister formRegister) {
        Set<Role> roles = new HashSet<>();
        if (formRegister.getRoles() == null || formRegister.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            formRegister.getRoles().forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                    case "moderator":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_MODERATOR));
                    case "user":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                    default:
                        throw new RuntimeException("Role not found!");
                }
            });
        }
        Date curDate = new Date();
        User user = User.builder()
                .fullname(formRegister.getFullName())
                .username(formRegister.getUserName())
                .email(formRegister.getEmail())
                .password(passwordEncoder.encode(formRegister.getPassword()))
                .phone(formRegister.getPhone())
                .address(formRegister.getFullAddress())
                .isDeleted(false)
                .updatedAt(curDate)
                .createdAt(curDate)
                .status(true)
                .roles(roles)
                .build();
        userRepository.save(user);
    }

    @Override
    public void update(FormUpdateUser formUpdateUser) {

    }
}