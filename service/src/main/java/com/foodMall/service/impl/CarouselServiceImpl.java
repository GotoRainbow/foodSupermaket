package com.foodMall.service.impl;

import com.foodMall.service.CarouselService;
import com.mapper.CarouselMapper;
import com.pojo.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> rerult = carouselMapper.selectByExample(example);
        return rerult;
    }
}
