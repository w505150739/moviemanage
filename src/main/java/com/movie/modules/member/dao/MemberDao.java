package com.movie.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.movie.modules.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-24 18:52:11
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

    /**
     * 根据条件查询数据
     * @param params
     * @return
     */
    List<MemberEntity> queryList(Map<String, Object> params);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);
}
