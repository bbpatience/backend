package com.walle.cplatform.course.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.course.bean.CourseBean;
import com.walle.cplatform.course.pojos.InputCourseInfo;
import com.walle.cplatform.course.pojos.OutputCourseInfo;
import com.walle.cplatform.course.service.CourseApplication;
import com.walle.cplatform.course.service.CourseService;
import com.walle.cplatform.utils.RestResultCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: bbpatience
 * @date: 2018/11/5
 * @description: CourseApplicationImpl
 **/
@Service
public class CourseApplicationImpl implements CourseApplication {

    private CourseService courseService;

    @Autowired
    public CourseApplicationImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public RestResult getCourses(Integer type, String uid, long from, long to) {
        List<CourseBean> beans = courseService.getCourses(type, uid, from, to);
        List<OutputCourseInfo> result = new ArrayList<>();
        beans.forEach(bean -> {
            OutputCourseInfo info = new OutputCourseInfo(bean.getUid(), bean.getCid(),
                bean.getTid(), bean.getDate(), bean.getId());
            result.add(info);
        });
        return RestResult.success(result);
    }

    @Override
    public RestResult getCourseById(long id) {
        CourseBean bean = courseService.getCourseById(id);
        if (bean != null) {
            OutputCourseInfo result = new OutputCourseInfo(bean.getUid(), bean.getCid(),
                bean.getTid(), bean.getDate(), bean.getId());
            return RestResult.success(result);
        } else {
            return RestResult.generate(RestResultCode.LOG_NOT_FOUND);
        }
    }

    @Override
    public RestResult addCourse(InputCourseInfo info) {
        if (StringUtils.isEmpty(info.getCid()) ||
            StringUtils.isEmpty(info.getTid()) ||
            StringUtils.isEmpty(info.getUid())) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        int id = courseService.addCourse(info);
        if (id == -1) {
            return RestResult.generate(RestResultCode.LOG_CUSTOMER_ID_INVALID);
        } else if (id == -2) {
            return RestResult.generate(RestResultCode.LOG_TEACHER_ID_INVALID);
        } else if (id == -3) {
            return RestResult.generate(RestResultCode.LOG_CLASS_ID_INVALID);
        }
        return RestResult.success();
    }

    @Override
    public RestResult getCourses(long from, long to) {
        List<CourseBean> beans = courseService.getCourses(from, to);
        List<OutputCourseInfo> result = new ArrayList<>();
        beans.forEach(bean -> {
            OutputCourseInfo info = new OutputCourseInfo(bean.getUid(), bean.getCid(),
                bean.getTid(), bean.getDate(), bean.getId());
            result.add(info);
        });
        return RestResult.success(result);
    }

    @Override
    public RestResult delCourseById(long id) {
        int result = courseService.delCourseById(id);
        if (result > 0) {
            return RestResult.success();
        } else {
            return RestResult.generate(RestResultCode.LOG_NOT_FOUND);
        }
    }
}
