package com.ifmo.isdb.strattanoakmant.controller;

import com.ifmo.isdb.strattanoakmant.controller.dto.LoginDto;
import com.ifmo.isdb.strattanoakmant.model.JwtToken;
import com.ifmo.isdb.strattanoakmant.model.Login;
import com.ifmo.isdb.strattanoakmant.service.ifc.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;
    private final MapperFacade mapperFacade;


    @PostMapping("/login")
    @ApiOperation(value = "Create token", response = JwtToken.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created token"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<JwtToken> getToken(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(tokenService.createToken(mapperFacade.map(loginDto, Login.class)));
    }
}
