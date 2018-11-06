package com.walle.cplatform.course.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.course.pojos.InputCourseInfo;
import com.walle.cplatform.course.service.CourseApplication;
import com.walle.cplatform.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/course", produces = APPLICATION_JSON_VALUE)
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private CourseApplication courseApplication;

    @Autowired
    public CourseController(CourseApplication courseApplication) {
        this.courseApplication = courseApplication;
    }

    @GetMapping
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getCourses (
        @RequestParam("from") long fromTime,
        @RequestParam("to") long toTime) {
        logger.info("Get Courses called. {} --- {}", fromTime, toTime);
        return courseApplication.getCourses(fromTime, toTime);
    }

    @GetMapping("/{uid}/list")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getCourses (
        @PathVariable("uid") String uid,
        @RequestParam(value = "type", defaultValue = "0") Integer type,
        @RequestParam("from") long fromTime,
        @RequestParam("to") long toTime) {
        logger.info("Get Courses called. {}", type);
        return courseApplication.getCourses(type, uid, fromTime, toTime);
    }

    @GetMapping("/{id}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getCourseById(@PathVariable("id") long id) {
        logger.info("Get Course By id called.");
        return courseApplication.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult delCourseById(@PathVariable("id") long id) {
        logger.info("Delete Course By id called.");
        return courseApplication.delCourseById(id);
    }

    @PostMapping
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult addCourse(@RequestBody InputCourseInfo info) {
        logger.info("add course called.");
        return courseApplication.addCourse(info);
    }
}
