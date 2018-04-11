package com.movie.modules.attachs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.modules.attachs.dao.AttachsDao;
import com.movie.modules.attachs.entity.AttachsEntity;
import com.movie.modules.attachs.service.AttachsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attachsService")
public class AttachsServiceImpl extends ServiceImpl<AttachsDao, AttachsEntity> implements AttachsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AttachsEntity> page = this.selectPage(
                new Query<AttachsEntity>(params).getPage(),
                new EntityWrapper<AttachsEntity>()
        );

        return new PageUtils(page);
    }

}
