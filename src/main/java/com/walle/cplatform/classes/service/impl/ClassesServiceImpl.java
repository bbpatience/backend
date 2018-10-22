package com.walle.cplatform.classes.service.impl;

import com.walle.cplatform.classes.enums.ClassState;
import com.walle.cplatform.classes.enums.ClassType;
import com.walle.cplatform.classes.bean.ClassBean;
import com.walle.cplatform.classes.mapper.ClassesMapper;
import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.utils.DateTimeUtils;
import com.walle.cplatform.utils.ShortUuid;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ClassesServiceImpl implements ClassesService {

    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    private ClassesMapper classesMapper;

    @Autowired
    public ClassesServiceImpl(ClassesMapper classesMapper) {
        this.classesMapper = classesMapper;
    }

    @Override
    public List<ClassBean> getClasses(Integer state) {
        logger.info("Get Classes");
        Example example = new Example(ClassBean.class);
        example.createCriteria().andEqualTo("state", state);
        return classesMapper.selectByExample(example);
    }

    @Override
    public ClassBean getClassByUid(String uid) {
        logger.info("Get Classes By uid {}", uid);
        ClassBean beanQuery = new ClassBean();
        beanQuery.setUid(uid);
        return classesMapper.selectOne(beanQuery);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String addClass(String name) {
        ClassBean beanQuery = new ClassBean();
        String uid = new ShortUuid.Builder().build().toString();
        beanQuery.setUid(uid);
        beanQuery.setName(name);
        Date date = DateTimeUtils.currentUTC();
        beanQuery.setCreateDt(date);
        beanQuery.setUpdateDt(date);
        beanQuery.setState(ClassState.NORMAL.getState());
        beanQuery.setType(ClassType.MASTER.getType());
        classesMapper.insert(beanQuery);
        return uid;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int delClass(String uid) {
        logger.info("del Class By uid {}", uid);
        ClassBean bean = getClassByUid(uid);
        if (bean.getState() == ClassState.DELETED.getState()) {
            return -1;
        }
        bean = new ClassBean();
        bean.setState(ClassState.DELETED.getState());
        bean.setUpdateDt(DateTimeUtils.currentUTC());

        Example example = new Example(ClassBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        return classesMapper.updateByExampleSelective(bean, example);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateClass(String newName, String uid) {
        logger.info("update Class By uid {}", uid);
        ClassBean beanQuery = new ClassBean();
        beanQuery.setUid(uid);
        ClassBean classesBean = classesMapper.selectOne(beanQuery);
        if (classesBean == null || classesBean.getState() == ClassState.DELETED.getState()) {
            return -1;
        }
        ClassBean bean = new ClassBean();
        bean.setName(newName);
        bean.setUpdateDt(DateTimeUtils.currentUTC());

        Example example = new Example(ClassBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        return classesMapper.updateByExampleSelective(bean, example);
    }
}
