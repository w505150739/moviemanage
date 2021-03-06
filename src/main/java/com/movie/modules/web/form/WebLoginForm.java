package com.movie.modules.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
@ToString
public class WebLoginForm {

    @NotBlank(message="用户名不能为空")
    private String userName;
    @NotBlank(message="密码不能为空")
    private String password;
}
