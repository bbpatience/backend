package com.walle.cplatform.classes.service;

import com.walle.cplatform.common.RestResult;

public interface ClassesService {
    RestResult getAllClasses();

    RestResult getClassByUid(String uid);

    RestResult addClass(String name);

    RestResult delClass(String uid);
}
