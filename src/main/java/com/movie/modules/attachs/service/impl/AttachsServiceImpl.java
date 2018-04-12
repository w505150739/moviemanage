package com.movie.modules.attachs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.modules.attachs.dao.AttachsDao;
import com.movie.modules.attachs.entity.AttachsEntity;
import com.movie.modules.attachs.service.AttachsService;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("attachsService")
@Transactional
public class AttachsServiceImpl extends ServiceImpl<AttachsDao, AttachsEntity> implements AttachsService {

    @Autowired
    private AttachsDao attachsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AttachsEntity> page = this.selectPage(
                new Query<AttachsEntity>(params).getPage(),
                new EntityWrapper<AttachsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<String> getUuidByMap(Map<String, Object> params) {
        List<String> uuidList = new ArrayList<String>();
        List<AttachsEntity> list = attachsDao.getUuidByMap(params);
        for (AttachsEntity attachsEntity : list){
            uuidList.add(attachsEntity.getFilePath());
        }
        return uuidList;
    }

}
