package com.mapper;

import com.pojo.vo.CategoryVO;

import java.util.List;


public interface CategoryMapperCustom {

    List<CategoryVO> getSubCatList(Integer rootCatId);
}
