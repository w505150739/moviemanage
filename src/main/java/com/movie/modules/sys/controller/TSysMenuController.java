package com.movie.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.movie.common.base.BaseController;
import com.movie.common.base.ResultData;
import com.movie.modules.sys.entity.TSysMenuEntity;
import com.movie.modules.sys.service.ShiroService;
import com.movie.modules.sys.service.TSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/sys/menu")
public class TSysMenuController extends BaseController {

    @Autowired
    private TSysMenuService menuService;

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/menulist")
    public ResultData queryMenuList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        List<TSysMenuEntity> tSysMenuList = menuService.selectList(new EntityWrapper<TSysMenuEntity>().eq("parent_id","0"));
        ResultData result = new ResultData().successResult("菜单获取成功",null);
        return result;
    }

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public ResultData nav(){
        List<TSysMenuEntity> menuList = menuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("menuList", menuList);
        map.put("permissions", permissions);
        return new ResultData(map);
    }

}
