package br.com.fiap.apinextgen.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        return verifyExistence(id);
    }


    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User update(Long id, User user) {
        verifyExistence(id);
        user.setId(id);
        return userRepository.save(user);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public User verifyExistence(Long id) {
        return userRepository.findById(id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "não existe usuário com o id informado"));
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
