package com.movie.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.movie.common.base.BaseController;
import com.movie.common.base.ResultData;
import com.movie.modules.sys.entity.TSysMenuEntity;
import com.movie.modules.sys.reqbean.MenuReqBean;
import com.movie.modules.sys.service.TSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class TSysMenuController extends BaseController {

    @Autowired
    private TSysMenuService menuService;

    @RequestMapping("/menulist")
    public ResultData queryMenuList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        List<TSysMenuEntity> tSysMenuList = menuService.selectList(new EntityWrapper<TSysMenuEntity>().eq("parent_id","0"));
        ResultData result = new ResultData().successResult("菜单获取成功",getChildren(tSysMenuList));
        return result;
    }

    /**
     * 递归获取菜单
     * @param tSysMenuList 父菜单
     * @return 返回菜单树
     */
    private List<MenuReqBean> getChildren(List<TSysMenuEntity> tSysMenuList){
        Map<String,Object> params = new HashMap<String,Object>();
        List<MenuReqBean> returnList = new ArrayList<MenuReqBean>();
        if(tSysMenuList != null && !tSysMenuList.isEmpty()){
            for (TSysMenuEntity menu : tSysMenuList) {
                MenuReqBean reqBean = new MenuReqBean();
                reqBean.setTitle(menu.getName());
                reqBean.setIcon(menu.getIcon());
                reqBean.setJump(menu.getUrl());
                params.put("pid",menu.getParentId());
                reqBean.setList(getChildren(menuService.selectList(new EntityWrapper<TSysMenuEntity>().eq("parent_id",menu.getParentId()))));
                returnList.add(reqBean);
            }
        }
        return returnList;
    }
}
