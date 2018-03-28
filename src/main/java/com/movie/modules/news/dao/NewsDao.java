package com.movie.modules.news.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.news.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 新闻资讯表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-28 22:29:33
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {
	
}
