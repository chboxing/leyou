package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 扫描controller注解所在的类
 */
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理自定义异常类
     *
     * @param e 自定义异常类
     * @return
     */
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handlerException(LyException e) {
        //获取自定义异常类中的枚举属性绑定的错误代号
        Integer code = e.getExceptionEnum().getCode();
        //通过构造函数传入自定义异常类中的枚举属性获取异常结果对象
        ExceptionResult exceptionResult = new ExceptionResult(e.getExceptionEnum());
        //返回响应实体
        return ResponseEntity.status(code).body(exceptionResult);
    }
}
