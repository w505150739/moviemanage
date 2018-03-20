package com.movie.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2016-03-13 15:50
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
