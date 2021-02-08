package com.leyou.common.vo;


import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long total; //总数
    private Long totalPage;//总页数
    private List<T> items;//当前集合数据

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage) {
        this.total = total;
        this.totalPage = totalPage;
    }



    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
