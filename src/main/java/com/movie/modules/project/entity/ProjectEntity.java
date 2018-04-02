package com.movie.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 项目表
 * 
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-03 00:08:48
 */
@TableName("t_b_project")
public class ProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 影片id
	 */
	private Long movieId;
	/**
	 * 项目成本
	 */
	private BigDecimal projectCost;
	/**
	 * 项目发起人
	 */
	private String projectHeader;
	/**
	 * 筹资金额
	 */
	private BigDecimal financingMoney;
	/**
	 * 投资开始时间
	 */
	private Timestamp startTime;
	/**
	 * 投资结束时间
	 */
	private Timestamp endTime;
	/**
	 * 项目标签 如 永久版权 永久福利等
	 */
	private String projectLabel;
	/**
	 * 项目服务费
	 */
	private BigDecimal projectCoverCharge;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 项目类型 1 网络电影 2 院线电影
	 */
	private Integer projectType;
	/**
	 * 项目状态 1 即将上线 2 正在募集 3 募集完成
	 */
	private Integer projectStatus;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;
	/**
	 * 状态 1、可用 2、已删除
	 */
	private Integer status;
	/**
	 * 审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过
	 */
	private Integer examineStatus;
	/**
	 * 是否发布 1 发布 0 未发布
	 */
	private Integer publishStatus;
	/**
	 * 项目介绍
	 */
	private String projectDes;
	/**
	 * 审核意见
	 */
	private String remark;

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
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：影片id
	 */
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	/**
	 * 获取：影片id
	 */
	public Long getMovieId() {
		return movieId;
	}
	/**
	 * 设置：项目成本
	 */
	public void setProjectCost(BigDecimal projectCost) {
		this.projectCost = projectCost;
	}
	/**
	 * 获取：项目成本
	 */
	public BigDecimal getProjectCost() {
		return projectCost;
	}
	/**
	 * 设置：项目发起人
	 */
	public void setProjectHeader(String projectHeader) {
		this.projectHeader = projectHeader;
	}
	/**
	 * 获取：项目发起人
	 */
	public String getProjectHeader() {
		return projectHeader;
	}
	/**
	 * 设置：筹资金额
	 */
	public void setFinancingMoney(BigDecimal financingMoney) {
		this.financingMoney = financingMoney;
	}
	/**
	 * 获取：筹资金额
	 */
	public BigDecimal getFinancingMoney() {
		return financingMoney;
	}
	/**
	 * 设置：投资开始时间
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：投资开始时间
	 */
	public Timestamp getStartTime() {
		return startTime;
	}
	/**
	 * 设置：投资结束时间
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：投资结束时间
	 */
	public Timestamp getEndTime() {
		return endTime;
	}
	/**
	 * 设置：项目标签 如 永久版权 永久福利等
	 */
	public void setProjectLabel(String projectLabel) {
		this.projectLabel = projectLabel;
	}
	/**
	 * 获取：项目标签 如 永久版权 永久福利等
	 */
	public String getProjectLabel() {
		return projectLabel;
	}
	/**
	 * 设置：项目服务费
	 */
	public void setProjectCoverCharge(BigDecimal projectCoverCharge) {
		this.projectCoverCharge = projectCoverCharge;
	}
	/**
	 * 获取：项目服务费
	 */
	public BigDecimal getProjectCoverCharge() {
		return projectCoverCharge;
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
	 * 设置：项目类型 1 网络电影 2 院线电影
	 */
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	/**
	 * 获取：项目类型 1 网络电影 2 院线电影
	 */
	public Integer getProjectType() {
		return projectType;
	}
	/**
	 * 设置：项目状态 1 即将上线 2 正在募集 3 募集完成
	 */
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	/**
	 * 获取：项目状态 1 即将上线 2 正在募集 3 募集完成
	 */
	public Integer getProjectStatus() {
		return projectStatus;
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
	 * 设置：审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过
	 */
	public void setExamineStatus(Integer examineStatus) {
		this.examineStatus = examineStatus;
	}
	/**
	 * 获取：审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过
	 */
	public Integer getExamineStatus() {
		return examineStatus;
	}
	/**
	 * 设置：是否发布 1 发布 0 未发布
	 */
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	/**
	 * 获取：是否发布 1 发布 0 未发布
	 */
	public Integer getPublishStatus() {
		return publishStatus;
	}
	/**
	 * 设置：项目介绍
	 */
	public void setProjectDes(String projectDes) {
		this.projectDes = projectDes;
	}
	/**
	 * 获取：项目介绍
	 */
	public String getProjectDes() {
		return projectDes;
	}
	/**
	 * 设置：审核意见
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：审核意见
	 */
	public String getRemark() {
		return remark;
	}
}
