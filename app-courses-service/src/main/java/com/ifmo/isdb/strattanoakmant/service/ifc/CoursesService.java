package com.ifmo.isdb.strattanoakmant.service.ifc;

import com.ifmo.isdb.strattanoakmant.model.Course;

import java.util.List;

public interface CoursesService {

    void publishActualCourses(List<Course> courses);

    List<Course> getActualCourses();
}
