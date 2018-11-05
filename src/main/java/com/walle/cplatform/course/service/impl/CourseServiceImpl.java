package com.walle.cplatform.course.service.impl;

import com.walle.cplatform.course.bean.CourseBean;
import com.walle.cplatform.course.mapper.CourseMapper;
import com.walle.cplatform.course.pojos.InputCourseInfo;
import com.walle.cplatform.course.service.CourseService;
import com.walle.cplatform.utils.DateTimeUtils;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(
        CourseServiceImpl.class);

    private CourseMapper classesMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper classesMapper) {
        this.classesMapper = classesMapper;
    }

    @Override
    public List<CourseBean> getCourses(Integer type, String uid) {
        return null;
    }

    @Override
    public CourseBean getCourseById(long id) {
        return null;
    }

    @Override
    public int addCourse(InputCourseInfo info) {
        CourseBean bean = new CourseBean();
        Date date = DateTimeUtils.currentUTC();
        bean.setCid(info.getCid());
        bean.setTid(info.getTid());
        bean.setUid(info.getUid());
        bean.setDate(date);
        return classesMapper.insert(bean);
    }

    @Override
    public List<CourseBean> getCourses(long from, long to) {
        Example example = new Example(CourseBean.class);
        Date fromDate = new Date(from);
        Date toDate = new Date(to);
        example.createCriteria().andBetween("date", fromDate, toDate);
        return classesMapper.selectByExample(example);
    }
}
