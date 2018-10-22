package com.walle.cplatform.classes.service;

import com.walle.cplatform.classes.bean.ClassBean;
import java.util.List;

public interface ClassesService {
    List<ClassBean> getClasses(Integer state);

    ClassBean getClassByUid(String uid);

    String addClass(String name);

    int delClass(String uid);

    int updateClass(String newName, String uid);
}
