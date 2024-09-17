package br.com.fiap.apinextgen.user;

import br.com.fiap.apinextgen.user.dto.UserRequest;
import br.com.fiap.apinextgen.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam(required = false) String name) {
        return userService
                    .findByName(name)
                    .stream()
                    .map(UserResponse::fromModel)
                    .toList();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        User user = userService.create(userRequest.toModel());

        var uri = uriBuilder
                        .path("/users/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();

        return ResponseEntity
                    .created(uri)
                    .body(UserResponse.fromModel(user));
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
