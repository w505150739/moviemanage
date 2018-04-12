package com.movie.modules.news.controller;

import com.movie.common.base.BaseController;
import com.movie.common.utils.GlobalContants;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.Query;
import com.movie.common.utils.R;
import com.movie.modules.attachs.service.AttachsService;
import com.movie.modules.news.entity.NewsEntity;
import com.movie.modules.news.service.NewsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 新闻资讯表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-28 22:29:33
 */
@RestController
@RequestMapping("sys/news")
public class NewsController extends BaseController{
    @Autowired
    private NewsService newsService;

    @Autowired
    private AttachsService attachsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:news:list")
    public R list(@RequestParam Map<String, Object> params){

        Query query = new Query(params);
        List<NewsEntity> userList = newsService.queryList(query);
        int total = newsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getCurrPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:news:info")
    public R info(@PathVariable("id") Long id){

        NewsEntity news = newsService.selectById(id);
        /** 根据id 和类型 获取附件列表 **/
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("relationId",id);
        params.put("relationType", GlobalContants.AttachType.NEWS.getValue());
        List<String> uuidList = attachsService.getUuidByMap(params);
        return R.ok().put("news", news).put("uuid",uuidList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:news:save")
    public R save(@RequestBody NewsEntity news){

        news.setStatus(1);
		newsService.insert(news);
        return R.ok().put("id",news.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:news:update")
    public R update(@RequestBody NewsEntity news){
        /** 删除当前记录已有的附件 **/
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("relationId",news.getId());
        params.put("relationType", GlobalContants.AttachType.NEWS.getValue());
        List<String> uuidList = attachsService.getUuidByMap(params);
        if(uuidList != null && !uuidList.isEmpty()){

        }
        newsService.updateById(news);

        return R.ok();
    }

    /**
     * 更新内容字段
     * @param request
     * @return
     */
    @RequestMapping("/updateContent")
    public R updateContent(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        newsService.updateContent(params);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:news:delete")
    public R delete(@RequestBody Long[] ids){
			newsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
