package com.movie.modules.news.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.modules.news.dao.NewsDao;
import com.movie.modules.news.entity.NewsEntity;
import com.movie.modules.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NewsEntity> page = this.selectPage(
                new Query<NewsEntity>(params).getPage(),
                new EntityWrapper<NewsEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public int updateContent(Map<String, Object> params) {

        return newsDao.updateContent(params);
    }

    @Override
    public List<NewsEntity> queryList(Map<String, Object> params) {
        return newsDao.queryList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newsDao.queryTotal(map);
    }

}
