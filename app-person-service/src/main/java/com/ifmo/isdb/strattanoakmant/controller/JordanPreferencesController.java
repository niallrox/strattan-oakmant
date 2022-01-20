package com.ifmo.isdb.strattanoakmant.controller;

import com.ifmo.isdb.strattanoakmant.model.Course;
import com.ifmo.isdb.strattanoakmant.model.JordanPreferences;
import com.ifmo.isdb.strattanoakmant.model.Role;
import com.ifmo.isdb.strattanoakmant.security.AccessRole;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/jordan-preferences")
@AccessRole({Role.JORDAN, Role.ANDREW})
@RequiredArgsConstructor
public class JordanPreferencesController {

    @GetMapping("/cocaine")
    @ApiOperation(value = "Get cocaine", response = JordanPreferences.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully returned cocaine"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<String> getCocaine(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JordanPreferences.COCAINE.getName());
    }

    @GetMapping("/whiskey")
    @ApiOperation(value = "Get whiskey", response = JordanPreferences.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully returned whiskey"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<String> getWhiskey(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JordanPreferences.WHISKEY.getName());
    }

    @GetMapping("/gong")
    @ApiOperation(value = "Get gong", response = JordanPreferences.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully returned gong"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<String> getGong(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(JordanPreferences.GONG.getName());
    }
}
