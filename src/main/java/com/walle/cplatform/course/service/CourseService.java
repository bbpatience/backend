package com.walle.cplatform.course.service;

import com.walle.cplatform.course.bean.CourseBean;
import com.walle.cplatform.course.pojos.InputCourseInfo;
import java.util.List;

public interface CourseService {
    List<CourseBean> getCourses(Integer type, String uid, long from, long to);
    CourseBean getCourseById(long id);
    int addCourse(InputCourseInfo info);
    List<CourseBean> getCourses(long from, long to);
    int delCourseById(long id);
}
