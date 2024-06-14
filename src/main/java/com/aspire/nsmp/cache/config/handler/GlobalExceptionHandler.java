package com.aspire.nsmp.cache.config.handler;

import com.aspire.nsmp.cache.dto.ResultDTO;
import com.aspire.nsmp.cache.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @Resource
//    MessageSource messageSource;

//    @ExceptionHandler({org.springframework.web.bind.MissingServletRequestParameterException.class})
//    @ResponseBody
//    public ResultDTO processRequestParameterException(HttpServletRequest request,
//                                                      HttpServletResponse response,
//                                                      MissingServletRequestParameterException e) {
//
//        response.setStatus(HttpStatus.FORBIDDEN.value());
//        response.setContentType("application/json;charset=UTF-8");
//        APIResponse result = new APIResponse();
//        result.setCode(ApiResultStatus.BAD_REQUEST.getApiResultStatus());
//        result.setMessage(
//                messageSource.getMessage(ApiResultStatus.BAD_REQUEST.getMessageResourceName(),
//                        null, LocaleContextHolder.getLocale()) + e.getParameterName());
//        return result;
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO processDefaultException(Exception e) {
        log.info("程序执行异常:[{}]",e);
        return ResultDTO.fail(e.getMessage());
    }


    /**
     * 内部微服务异常统一处理方法
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultDTO processUnifiedException(BizException e) {
        return ResultDTO.fail(e.getMsg());
    }


}
