package module4.projectmd4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import module4.projectmd4.constant.EHttpStatus;
import module4.projectmd4.exception.CustomException;
import module4.projectmd4.model.dto.request.FormLogin;
import module4.projectmd4.model.dto.request.FormRegister;
import module4.projectmd4.model.dto.response.ResponseWrapper;
import module4.projectmd4.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@Valid @RequestBody FormLogin formLogin) throws CustomException {
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .eHttpStatus(EHttpStatus.SUCCESS)
                        .statusCode(HttpStatus.OK.value())
                        .data(authService.handleLogin(formLogin))
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> handleRegister(@Valid @RequestBody FormRegister formRegister) {
        authService.handleRegister(formRegister);
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .eHttpStatus(EHttpStatus.SUCCESS)
                        .statusCode(HttpStatus.CREATED.value())
                        .data("Registered successfully!")
                        .build(),
                HttpStatus.CREATED);
    }

}