package com.movie.modules.project.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class ApprovalForm {

    @NotNull(message = "[id]参数不能为空")
    private Long id;
    private String remark;
    @NotNull(message = "[examineStatus]审核结果不能空")
    private Integer examineStatus;
}
