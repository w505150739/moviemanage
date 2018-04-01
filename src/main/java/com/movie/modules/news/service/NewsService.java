package com.movie.modules.news.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.utils.PageUtils;
import com.movie.modules.news.entity.NewsEntity;

import java.util.List;
import java.util.Map;

/**
 * 新闻资讯表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-28 22:29:33
 */
public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    int updateContent(Map<String,Object> params);

    /**
     * 根据条件查询数据
     * @param params
     * @return
     */
    List<NewsEntity> queryList(Map<String, Object> params);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);
}

