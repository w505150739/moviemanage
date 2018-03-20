package com.movie.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.sys.entity.TSysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TSysMenuDao extends BaseMapper<TSysMenuEntity>{
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<TSysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<TSysMenuEntity> queryNotButtonList();
}
