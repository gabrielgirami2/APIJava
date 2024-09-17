package br.com.fiap.apinextgen.user.dto;

import br.com.fiap.apinextgen.user.User;

public record UserResponse(
        Long id,
        String name,
        String gender,
        Boolean aged
) {

    public static UserResponse fromModel(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getGender(), user.isAged());
    }

}
