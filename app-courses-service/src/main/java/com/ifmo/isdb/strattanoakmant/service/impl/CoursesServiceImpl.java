package com.ifmo.isdb.strattanoakmant.service.impl;

import com.ifmo.isdb.strattanoakmant.model.Course;
import com.ifmo.isdb.strattanoakmant.service.ifc.CoursesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CoursesServiceImpl implements CoursesService {

    private static final Logger log =
            LoggerFactory.getLogger(CoursesServiceImpl.class);

    private List<Course> actual = new CopyOnWriteArrayList<>(defaults());

    private List<Course> defaults() {
        return Arrays.asList(new Course("BMW", 12L), new Course("ANUS", 13L));
    }

    @Override
    public void publishActualCourses(List<Course> courses) {
        log.debug("Publishing new actual courses");
        actual = new CopyOnWriteArrayList<>(courses);
        log.debug(String.format("Published new actual courses with size = %d", courses.size()));
    }

    @Override
    public List<Course> getActualCourses() {
        return Optional
                .ofNullable(actual)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Not found actual courses at %s", LocalDateTime.now())));
    }
}
