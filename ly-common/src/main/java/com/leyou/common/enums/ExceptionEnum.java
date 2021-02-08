package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    BRAND_NOT_FOUND(404, "该品牌不存在"),
    NAME_CANNOT_BE_NULL(404, "名称不能为空"),
    CATEGORY_NOT_FOUND(404, "商品分类未找到"),
    BRAND_SAVE_ERROR(500, "品牌新增失败"),
    INVALID_FILE_TYPE(500, "无效文件类型"),
    UPLOAD_FILE_ERROR(500, "文件上传失败"),
    ;//必须设置分号，分号做为分割

    private Integer code;
    private String msg;

}
