package com.walle.cplatform.classes.service.impl;

import com.walle.cplatform.classes.bean.ClassBean;
import com.walle.cplatform.classes.enums.ClassState;
import com.walle.cplatform.classes.pojos.OutputClassInfo;
import com.walle.cplatform.classes.service.ClassesApplication;
import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.pojos.OutputId;
import com.walle.cplatform.utils.RestResultCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: bbpatience
 * @date: 2018/10/22
 * @description: ClassesApplicationImpl
 **/
@Service
public class ClassesApplicationImpl implements ClassesApplication {

    private ClassesService classesService;

    @Autowired
    public ClassesApplicationImpl(ClassesService classesService) {
        this.classesService = classesService;
    }

    @Override
    public RestResult getClasses(Integer state) {
        if (ClassState.DELETED.getState() < state ||
            ClassState.NORMAL.getState() > state) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        List<ClassBean> beans = classesService.getClasses(state);
        List<OutputClassInfo> out = new ArrayList<>();
        if (beans == null) {
            return RestResult.generate(RestResultCode.CLASS_CLASS_NOT_FOUND);
        }
        beans.forEach(classBean -> out.add(new OutputClassInfo(classBean)));
        return RestResult.success().setData(out);
    }

    @Override
    public RestResult getClassByUid(String uid) {
        ClassBean classesBean = classesService.getClassByUid(uid);
        if (classesBean == null) {
            return RestResult.generate(RestResultCode.CLASS_CLASS_NOT_FOUND);
        }
        OutputClassInfo out = new OutputClassInfo(classesBean);
        return RestResult.success().setData(out);
    }

    @Override
    public RestResult addClass(String name) {
        String uid = classesService.addClass(name);
        return RestResult.success(new OutputId(uid));
    }

    @Override
    public RestResult delClass(String uid) {
        int result = classesService.delClass(uid);
        if (result == -1) {
            return RestResult.generate(RestResultCode.CLASS_CLASS_DELETED_ALREADY);
        } else if (result <= 0) {
            return RestResult.generate(RestResultCode.CLASS_CLASS_NOT_FOUND);
        }
        return RestResult.success();
    }

    @Override
    public RestResult updateClass(String newName, String uid) {
        int result = classesService.updateClass(newName, uid);
        if (result == -1) {
            return RestResult.generate(RestResultCode.CLASS_CLASS_NOT_FOUND);
        } else if (result <= 0) {
            RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        return RestResult.success();
    }
}
