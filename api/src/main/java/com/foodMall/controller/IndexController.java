package com.foodMall.controller;

import com.enums.YesOrNo;
import com.foodMall.service.CarouselService;
import com.foodMall.service.CategoryService;
import com.pojo.Carousel;
import com.pojo.Category;
import com.pojo.vo.CategoryVO;
import com.pojo.vo.NewItemsVO;
import com.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "展示轮播图",notes = "展示轮播图",httpMethod = "GET")
    @GetMapping("carousel")
    public IMOOCJSONResult carousel(){
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(list);
    }


    /**
     * 首页分类展示需求：
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "查询商品大分类" , notes = "查询商品大分类" ,httpMethod = "GET")
    @GetMapping("cats")
    public IMOOCJSONResult cats(){
        List<Category> result = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(result);
    }

    @ApiOperation(value = "查询商品大分类下的子分类" , notes = "查询商品大分类下的子分类" ,httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId){

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> result = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(result);
    }


    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(map);
        return IMOOCJSONResult.ok(list);
    }
}
