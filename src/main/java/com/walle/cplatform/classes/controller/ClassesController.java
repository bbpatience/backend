package com.walle.cplatform.classes.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.common.RestResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/classes", produces = APPLICATION_JSON_VALUE)
public class ClassesController {

    private static final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    private ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping
    @RequiresPermissions("*")
//    @RequiresPermissions(value = {"*", "eab:manage"}, logical = Logical.OR)
    public @ResponseBody RestResult getAllClasses() {
        logger.info("Get Classes All called.");
        return classesService.getAllClasses();
    }

    @GetMapping("/{uid}")
    @RequiresPermissions("*")
    public @ResponseBody RestResult getClassesByUid(@PathVariable("uid") String uid) {
        logger.info("Get Class By Uid called.");
        return classesService.getClassByUid(uid);
    }
}
