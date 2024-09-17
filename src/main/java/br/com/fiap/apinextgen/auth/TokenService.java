package br.com.fiap.apinextgen.auth;

import br.com.fiap.apinextgen.user.User;
import br.com.fiap.apinextgen.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final UserRepository userRepository;
    Algorithm algorithm;

    public TokenService(UserRepository userRepository, @Value("${jwt.secret}") String secret) {
        algorithm = Algorithm.HMAC256(secret);
        this.userRepository = userRepository;
    }

    public Token createToken(String email) {

        String token = JWT.create()
                .withSubject(email)
                .withIssuer("nextgen")
                .sign(algorithm);

        return new Token(token, "JWT");
    }

    public User getUserByToken(String token) {
        var email = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
