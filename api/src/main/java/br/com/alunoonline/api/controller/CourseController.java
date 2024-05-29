package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Course;
import br.com.alunoonline.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")

public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Course course) {courseService.create(course);}

}
