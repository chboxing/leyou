package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据商品分类ID查询分类集合
     * @param pid
     * @return
     */
    public List<Category> queryCategoryListByPid(Long pid) {
        //设置商品分类对象
        Category category = new Category();
        //设置非空属性，将对象作为参数传入select(category)方法中
        category.setParentId(pid);
        //会自动根据对象中非空属性作文条件去数据库查询
        List<Category> categoryList = categoryMapper.select(category);
        //为空判断，抛出自定义异常
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        //返回商品分类对象集合
        return categoryList;
    }
}
