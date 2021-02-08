package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

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
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){
        //调用接口
        PageResult<Brand> result = brandService.queryBrandByPage(page, rows, sortBy, desc, key);
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * @ResponseEntity<Void> 无返回值时设置Void
     * @param cids 分类集合
     * @param brand 品牌对象
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(@RequestParam("cids")List<Long> cids,Brand brand){
        //调用接口
        brandService.saveBrand(cids,brand);
        //只返回状态码
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
