package com.ifmo.isdb.strattanoakmant.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "name", example = "Maxim")
    @NotNull(message = "Name is mandatory")
    @Size(min = 1, message = "Password must be from 1 to 255 characters")
    private String name;

    @ApiModelProperty(value = "surname", example = "Zalupenko")
    @NotNull(message = "Surname is mandatory")
    @Size(min = 1, message = "Surname must be from 1 to 255 characters")
    private String surname;

    @ApiModelProperty(value = "education", example = "ITMO FITIP")
    private String education;

    @ApiModelProperty(value = "about yourself", example = "Loser")
    private String aboutYourself;

    @ApiModelProperty(value = "phone number", example = "8999999999")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;
}
