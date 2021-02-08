package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * 定义异常结果类
 */
@Data
public class ExceptionResult {

    private Integer status;
    private String message;
    private Long timestamp;

    /**
     * 构造方法赋值
     *
     * @param em 枚举对象
     */
    public ExceptionResult(ExceptionEnum em) {

        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
