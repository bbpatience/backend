package com.walle.cplatform.classes.service;

import com.walle.cplatform.common.RestResult;

public interface ClassesApplication {
    RestResult getClasses(Integer state);

    RestResult getClassByUid(String uid);

    RestResult addClass(String name);

    RestResult delClass(String uid);

    RestResult updateClass(String newName, String uid);
}
