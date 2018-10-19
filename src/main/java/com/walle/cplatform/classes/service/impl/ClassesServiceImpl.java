package com.walle.cplatform.classes.service.impl;

import com.walle.cplatform.classes.enums.ClassState;
import com.walle.cplatform.classes.enums.ClassType;
import com.walle.cplatform.classes.pojos.OutputClassInfo;
import com.walle.cplatform.classes.bean.ClassBean;
import com.walle.cplatform.classes.mapper.ClassesMapper;
import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.utils.RestResultCode;
import com.walle.cplatform.utils.ShortUuid;
import java.sql.Date;
import java.util.ArrayList;
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
    public RestResult getAllClasses() {
        logger.info("Get All Classes");
        List<ClassBean> classesBean = classesMapper.selectAll();

        List<OutputClassInfo> out = new ArrayList<>();
        classesBean.forEach(classBean -> out.add(new OutputClassInfo(classBean)));
        return RestResult.success().setData(out);
    }

    @Override
    public RestResult getClassByUid(String uid) {
        logger.info("Get Classes By uid {}", uid);
        ClassBean beanQuery = new ClassBean();
        beanQuery.setUid(uid);
        ClassBean classesBean = classesMapper.selectOne(beanQuery);
        if (classesBean == null) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        OutputClassInfo out = new OutputClassInfo(classesBean);
        return RestResult.success().setData(out);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResult addClass(String name) {
        ClassBean beanQuery = new ClassBean();
        beanQuery.setUid(new ShortUuid.Builder().build().toString());
        beanQuery.setName(name);
        Date date = new Date(System.currentTimeMillis());
        beanQuery.setCreateDt(date);
        beanQuery.setUpdateDt(date);
        beanQuery.setState(ClassState.NORMAL.getState());
        beanQuery.setType(ClassType.MASTER.getType());
        classesMapper.insert(beanQuery);
        return RestResult.success();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResult delClass(String uid) {
        logger.info("del Class By uid {}", uid);
        ClassBean bean = new ClassBean();
        bean.setState(ClassState.DELETED.getState());

        Example example = new Example(ClassBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        int result = classesMapper.updateByExampleSelective(bean, example);
        if (result <= 0) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        return RestResult.success();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestResult updateClass(String newName, String uid) {
        logger.info("update Class By uid {}", uid);
        ClassBean bean = new ClassBean();
        bean.setName(newName);

        Example example = new Example(ClassBean.class);
        example.createCriteria().andEqualTo("uid", uid);
        int result = classesMapper.updateByExampleSelective(bean, example);
        if (result <= 0) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        return RestResult.success();
    }
}
