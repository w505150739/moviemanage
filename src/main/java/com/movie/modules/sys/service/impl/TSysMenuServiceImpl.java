package com.movie.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.movie.modules.sys.dao.TSysMenuDao;
import com.movie.modules.sys.entity.TSysMenuEntity;
import com.movie.modules.sys.service.TSysMenuService;
import org.springframework.stereotype.Service;

@Service
public class TSysMenuServiceImpl extends ServiceImpl<TSysMenuDao,TSysMenuEntity> implements TSysMenuService {
}
