package com.mapper;

import com.pojo.vo.CategoryVO;
import com.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface CategoryMapperCustom {

    List<CategoryVO> getSubCatList(Integer rootCatId);

    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}
