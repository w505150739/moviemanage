package com.movie.modules.attachs.service;

import com.baomidou.mybatisplus.service.IService;
import com.movie.common.utils.PageUtils;
import com.movie.modules.attachs.entity.AttachsEntity;

import java.util.Map;

/**
 * 附件记录表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-12 02:08:33
 */
public interface AttachsService extends IService<AttachsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

