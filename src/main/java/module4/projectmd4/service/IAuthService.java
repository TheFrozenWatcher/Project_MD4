package module4.projectmd4.service;

import module4.projectmd4.exception.CustomException;
import module4.projectmd4.model.dto.request.FormLogin;
import module4.projectmd4.model.dto.request.FormRegister;
import module4.projectmd4.model.dto.request.FormUpdateUser;
import module4.projectmd4.model.dto.response.JwtResponse;

public interface IAuthService {

    JwtResponse handleLogin(FormLogin formLogin) throws CustomException;

    void handleRegister(FormRegister formRegister);
    void update(FormUpdateUser formUpdateUser);
}
