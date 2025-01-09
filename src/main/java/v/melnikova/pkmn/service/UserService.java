package v.melnikova.pkmn.service;

import v.melnikova.pkmn.dto.UserDTO;

import javax.security.auth.login.CredentialException;

public interface UserService {
    String loginUser(UserDTO userDTO) throws CredentialException;
    void registerUser(UserDTO userDTO);
}
