package com.walle.cplatform.common;

import com.walle.cplatform.utils.Constants;
import com.walle.cplatform.utils.RestResultCode;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理 处理控制器抛出的异常 Created by zhouxiaoxiao on 17/4/19.
 */
@ControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BaseException.class)
    public @ResponseBody RestResult handleValidationException(BaseException baseException) {
        log.error(baseException.getMessage());
        return new RestResult().setRspCode(RestResultCode.COMMON_SERVER_ERROR.getCode())
            .setRspMsg(baseException.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public @ResponseBody RestResult handleSQLException(SQLException sqlException) {
        log.error(sqlException.getMessage());
        sqlException.printStackTrace();
        return RestResult.failure();
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody RestResult handleException(Exception exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return RestResult.failure();
    }
}
