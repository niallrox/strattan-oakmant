package com.ifmo.isdb.strattanoakmant.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    @ApiModelProperty(value = "login", example = "m.rybalchenko")
    @NotNull(message = "Login is mandatory.")
    @Size(min = 1, message = "Login must be from 1 to 255 characters")
    private String login;

    @ApiModelProperty(value = "password", example = "12341dsadsa3")
    @NotNull(message = "Password is mandatory.")
    @Size(min = 1, message = "Password must be from 1 to 255 characters")
    private String password;
}
