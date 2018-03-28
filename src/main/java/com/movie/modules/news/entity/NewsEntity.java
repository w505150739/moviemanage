package com.movie.modules.news.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 新闻资讯表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-03-28 22:29:33
 */
@TableName("t_b_news")
public class NewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 资讯标题
	 */
	private String title;
	/**
	 * 资讯类型
	 */
	private Integer type;
	/**
	 * 状态 1、可用 2、已删除
	 */
	private Integer status;
	/**
	 * 是否显示 1 显示 0 不显示
	 */
	private Integer showFlag;
	/**
	 * 资讯内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：资讯标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：资讯标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：资讯类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：资讯类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：状态 1、可用 2、已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1、可用 2、已删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否显示 1 显示 0 不显示
	 */
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}
	/**
	 * 获取：是否显示 1 显示 0 不显示
	 */
	public Integer getShowFlag() {
		return showFlag;
	}
	/**
	 * 设置：资讯内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：资讯内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
}
