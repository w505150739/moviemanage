package com.movie.common.validator;

import com.movie.common.exception.GlobalException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2016-03-13 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new GlobalException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new GlobalException(message);
        }
    }
}
