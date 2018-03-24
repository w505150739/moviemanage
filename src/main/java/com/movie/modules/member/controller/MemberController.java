package com.movie.modules.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.movie.modules.member.entity.MemberEntity;
import com.movie.modules.member.service.MemberService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.movie.common.utils.PageUtils;
import com.movie.common.utils.R;



/**
 * 
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-24 18:52:11
 */
@RestController
@RequestMapping("sys/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:member:info")
    public R info(@PathVariable("id") Long id){

        MemberEntity member = memberService.selectById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:member:save")
    public R save(@RequestBody MemberEntity member){

        memberService.insert(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:member:update")
    public R update(@RequestBody MemberEntity member){
			memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:member:delete")
    public R delete(@RequestBody Long[] ids){
			memberService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
