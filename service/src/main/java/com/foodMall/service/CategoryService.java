package com.foodMall.service;

import com.pojo.Category;
import com.pojo.vo.CategoryVO;
import com.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 通过父类id查询所有子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);


    /**
     * 查询出最新的六个商品数据
     * @param map
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Map<String,Object> map);

}
