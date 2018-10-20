package com.walle.cplatform.classes.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.walle.cplatform.classes.pojos.InputClassInfo;
import com.walle.cplatform.classes.service.ClassesService;
import com.walle.cplatform.common.RestResult;
import com.walle.cplatform.utils.Constants;
import com.walle.cplatform.utils.RestResultCode;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
//    @RequiresPermissions("*")
//    @RequiresPermissions(value = {"*", "eab:manage"}, logical = Logical.OR)
    public @ResponseBody RestResult getClasses (
        @RequestParam(value = "state", defaultValue = "0", required = false) Integer state) {
        logger.info("Get Classes called. {}", state);
        return classesService.getClasses(state);
    }

    @GetMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult getClassesByUid(@PathVariable("uid") String uid) {
        logger.info("Get Class By Uid called.");
        return classesService.getClassByUid(uid);
    }

    @PutMapping("/add")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult addClass(@RequestBody InputClassInfo info) {
        logger.info("add classes. {}", info.getName());

        if (StringUtils.isEmpty(info.getName())) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        return classesService.addClass(info.getName());
    }

    @DeleteMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult delClass(@PathVariable("uid") String uid) {
        logger.info("delete class uid {}", uid);

        return classesService.delClass(uid);
    }

    @PostMapping("/{uid}")
    @RequiresRoles(Constants.USER_SUPER_ADMIN)
    public @ResponseBody RestResult updateClass(@PathVariable("uid") String uid,
            @RequestBody InputClassInfo info) {
        logger.info("update classes. {}", info.getName());

        if (StringUtils.isEmpty(info.getName())) {
            return RestResult.generate(RestResultCode.COMMON_INVALID_PARAMETER);
        }
        return classesService.updateClass(info.getName(), uid);
    }
}
