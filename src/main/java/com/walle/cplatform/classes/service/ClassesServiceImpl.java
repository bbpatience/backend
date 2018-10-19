package com.walle.cplatform.classes.service;

import com.walle.cplatform.classes.pojos.OutputClassInfo;
import com.walle.cplatform.classes.bean.ClassBean;
import com.walle.cplatform.classes.mapper.ClassesMapper;
import com.walle.cplatform.classes.service.impl.ClassesService;
import com.walle.cplatform.common.RestResult;
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
