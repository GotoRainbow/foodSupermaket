package com.foodMall.service;

import com.pojo.Carousel;
import com.pojo.vo.CategoryVO;

import java.util.List;

public interface CarouselService {

    /**
     * 查询所有轮播图
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}
