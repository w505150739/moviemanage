package com.movie.modules.news.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.news.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻资讯表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-28 22:29:33
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {

    int updateContent(Map<String,Object> params);

    List<NewsEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);
}
