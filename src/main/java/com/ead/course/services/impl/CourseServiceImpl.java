package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(CourseModel courseModel) {
        List<ModuleModel> modulesModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
        if (!modulesModelList.isEmpty()) {
            for (ModuleModel module : modulesModelList) {
                List<LessonModel> lessonsModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if (!lessonsModelList.isEmpty()) {
                    lessonRepository.deleteAll(lessonsModelList);
                }
            }
            moduleRepository.deleteAll(modulesModelList);
        }
        courseRepository.delete(courseModel);
    }
}
