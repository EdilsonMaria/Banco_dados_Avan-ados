package br.com.alunoonline.api.service;

import br.com.alunoonline.api.enums.CourseTypeEnum;
import br.com.alunoonline.api.model.Course;
import br.com.alunoonline.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public void create(Course course) {
        course.setType(CourseTypeEnum.GRADUACAO);
        courseRepository.save(course);
    }
}
