package com.movie.modules.attachs.controller;

import com.movie.common.base.BaseController;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.R;
import com.movie.modules.attachs.entity.AttachsEntity;
import com.movie.modules.attachs.service.AttachsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/**
 * 附件记录表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-12 02:08:33
 */
@RestController
@RequestMapping("project/attachs")
public class AttachsController extends BaseController{
    @Autowired
    private AttachsService attachsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:attachs:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attachsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:attachs:info")
    public R info(@PathVariable("id") Long id){
			AttachsEntity attachs = attachsService.selectById(id);

        return R.ok().put("attachs", attachs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("project:attachs:save")
    public R save(@RequestBody AttachsEntity attachs){

        attachsService.insert(attachs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:attachs:update")
    public R update(@RequestBody AttachsEntity attachs){
			attachsService.updateById(attachs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:attachs:delete")
    public R delete(@RequestBody Long[] ids){
			attachsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/deleteByUuid")
    public R deleteByUuid(@RequestBody String [] deluuid){
        for (int i = 0;i<deluuid.length;i++){
            Map<String,Object> params = new HashMap<>();
            String uuid = deluuid[i].substring( deluuid[i].lastIndexOf( "=" )+1 );
            params.put("file_path",uuid);
            attachsService.deleteByMap(params);
        }
        return R.ok();
    }
}
