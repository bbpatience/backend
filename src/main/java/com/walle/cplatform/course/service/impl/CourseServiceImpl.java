package com.walle.cplatform.course.service.impl;

import com.walle.cplatform.classes.bean.ClassBean;
import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.course.bean.CourseBean;
import com.walle.cplatform.course.enums.InputType;
import com.walle.cplatform.course.mapper.CourseMapper;
import com.walle.cplatform.course.pojos.InputCourseInfo;
import com.walle.cplatform.course.service.CourseService;
import com.walle.cplatform.user.bean.UserBean;
import com.walle.cplatform.user.enums.UserType;
import com.walle.cplatform.user.service.UserService;
import com.walle.cplatform.utils.DateTimeUtils;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private UserService userService;
    private ClassesService classesService;
    private CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, UserService userService, ClassesService classesService) {
        this.courseMapper = courseMapper;
        this.userService = userService;
        this.classesService = classesService;
    }

    @Override
    public List<CourseBean> getCourses(Integer type, String uid, long from, long to) {
        Example example = new Example(CourseBean.class);
        Date fromDate = new Date(from);
        Date toDate = new Date(to);
        Criteria criteria = example.createCriteria();
        criteria.andBetween("date", fromDate, toDate);
        if (type == InputType.CUSTOMER.getType()) {
            criteria.andEqualTo("uid", uid);
        } else if (type == InputType.CLASS.getType()) {
            criteria.andEqualTo("cid", uid);
        } else if (type == InputType.TEACHER.getType()) {
            criteria.andEqualTo("tid", uid);
        }
        return courseMapper.selectByExample(example);
    }

    @Override
    public CourseBean getCourseById(long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addCourse(InputCourseInfo info) {
        UserBean userBean = userService.getUser(info.getUid());
        if (userBean == null || userBean.getType() != UserType.CUSTOMER.getType()) {
            return -1;
        }
        UserBean teacherBean = userService.getUser(info.getTid());
        if (teacherBean == null || teacherBean.getType() != UserType.TEACHER.getType()) {
            return -2;
        }
        ClassBean classBean = classesService.getClassByUid(info.getCid());
        if (classBean == null) {
            return -3;
        }
        CourseBean bean = new CourseBean();
        Date date = DateTimeUtils.currentUTC();
        bean.setCid(info.getCid());
        bean.setTid(info.getTid());
        bean.setUid(info.getUid());
        bean.setDate(date);
        return courseMapper.insert(bean);
    }

    @Override
    public List<CourseBean> getCourses(long from, long to) {
        Example example = new Example(CourseBean.class);
        Date fromDate = new Date(from);
        Date toDate = new Date(to);
        example.createCriteria().andBetween("date", fromDate, toDate);
        return courseMapper.selectByExample(example);
    }

    @Override
    public int delCourseById(long id) {
        return courseMapper.deleteByPrimaryKey(id);
    }
}
