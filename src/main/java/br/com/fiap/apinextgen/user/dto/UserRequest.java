package br.com.fiap.apinextgen.user.dto;

import br.com.fiap.apinextgen.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserRequest(
     String name,
     String email,
     String password,
     String gender,
     Boolean aged,
     BigDecimal timeOfService,
     Boolean satisfied
) {

    public User toModel() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .registrationDate(LocalDate.now())
                .gender(gender)
                .aged(aged)
                .timeOfService(timeOfService)
                .exitForecast(LocalDate.now().plusYears(2))
                .isSatisfied(satisfied)
                .build();

    }

}
