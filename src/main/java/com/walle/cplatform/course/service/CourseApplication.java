package com.walle.cplatform.course.service;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.course.pojos.InputCourseInfo;

/**
 * @author: bbpatience
 * @date: 2018/11/5
 * @description: CourseApplicationImpl
 **/
public interface CourseApplication {
    RestResult getCourses(Integer type, String uid, long from, long to);
    RestResult getCourseById(long id);
    RestResult addCourse(InputCourseInfo info);
    RestResult getCourses(long from, long to);
    RestResult delCourseById(long id);
}
