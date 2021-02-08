package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌分页
     *
     * @param page   当前页码
     * @param rows   每页步长
     * @param sortBy 排序字段
     * @param desc   是否排序
     * @param key    搜索关键词
     * @return
     */
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        //分页，开启分页助手，传入点前页和步长
        PageHelper.startPage(page, rows);
        //创建example对象
        Example example = new Example(Brand.class);
        //过滤，判断搜索不为空
        if (StringUtils.isNoneBlank(key)) {
            example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)) {
            //此处必须加空格    + " " +
            String orderByClause = sortBy + " " + (desc ? "DESC" : "ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询结果集
        List<Brand> list = brandMapper.selectByExample(example);
        //为空时抛出异常
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //解析结果集
        PageInfo<Brand> pageInfo = new PageInfo<>(list);

        return new PageResult<Brand>(pageInfo.getTotal(), list);
    }

    /**
     * 新增品牌
     * @Transactional 开启事务
     * @param cids
     * @param brand
     */
    @Transactional
    public void saveBrand(List<Long> cids, Brand brand) {

        //设置品牌ID为空，ID自增长
        brand.setId(null);
        //新增品牌
        int count = brandMapper.insert(brand);
        //失败时抛出异常
        if (count != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //遍历分类ID
        for (Long cid : cids) {
            //新增分类品牌中间表
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            //失败时抛出异常
            if (count != 1) {
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }

    }
}
