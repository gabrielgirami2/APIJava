package br.com.fiap.apinextgen.auth;

import br.com.fiap.apinextgen.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Token login(Credentials credentials) {
        // acha o user, se não retorna um erro
        var user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // se a senha não combina, lança exceção
        if (!passwordEncoder.matches(credentials.password(), user.getPassword()))
            throw new RuntimeException("Access Denied");

        // retorna o createToken
        return tokenService.createToken(credentials.email());
    }
}
