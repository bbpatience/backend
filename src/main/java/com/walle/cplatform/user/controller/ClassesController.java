package com.walle.cplatform.user.controller;

import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.user.service.ClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/classes")
public class ClassesController {

    private static final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    private ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping
    public @ResponseBody RestResult getAllClasses() {
        logger.info("Get Classes All called.");
        return classesService.getAllClasses();
    }
}
