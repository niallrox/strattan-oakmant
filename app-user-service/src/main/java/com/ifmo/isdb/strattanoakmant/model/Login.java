package com.ifmo.isdb.strattanoakmant.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @NotNull(message = "Login is mandatory.")
    @Size(min = 1, message = "Login must be from 1 to 255 characters")
    private String login;

    @NotNull(message = "Password is mandatory.")
    @Size(min = 1, message = "Password must be from 1 to 255 characters")
    private String password;
}