package com.walle.cplatform.user.service.impl;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.bean.ClassBean;
import com.walle.cplatform.user.mapper.ClassesMapper;
import com.walle.cplatform.user.pojos.OutputClassInfo;
import com.walle.cplatform.user.service.ClassesService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
