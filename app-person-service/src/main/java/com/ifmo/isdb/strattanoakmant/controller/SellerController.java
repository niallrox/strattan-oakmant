package com.ifmo.isdb.strattanoakmant.controller;

import com.ifmo.isdb.strattanoakmant.model.Course;
import com.ifmo.isdb.strattanoakmant.model.Role;
import com.ifmo.isdb.strattanoakmant.security.AccessRole;
import com.ifmo.isdb.strattanoakmant.service.ifc.CoursesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
@AccessRole({Role.SELLER})
public class SellerController {

    private final CoursesService coursesService;

    @GetMapping("/courses")
    @ApiOperation(value = "Get actual courses", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created token"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<List<Course>> getActualCourses(@RequestHeader("authorization") String token) {
        return ResponseEntity.ok(coursesService.getActualCourses());
    }
}
