package me.emou.xworker.aop;


import me.emou.xworker.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * 异常处理
 * @author wangdao
 */
@ControllerAdvice
public class SystemExceptionHandler {

    /**
     * 处理 BusinessException 类型异常
     * @param request request
     * @param ex businessException
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBusinessException(WebRequest request, BusinessException ex) {
        String msg = ex.getMessage();
        if (StringUtils.isEmpty(msg)) {
            msg = "业务异常";
        }
        return msg;
    }



}
