package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
/**
 * 自定义异常类，继承RuntimeException
 */
public class LyException extends RuntimeException{

    //将枚举类作为属性，方便获取枚举中的status和msg
    private ExceptionEnum exceptionEnum;
}
