package com.movie.modules.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

@Setter
@Getter
@ToString
public class RegisterForm {
    /**
     * 手机号
     */
    @NotBlank(message="手机号不能为空")
    private String phone;
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    private String password;

    @NotBlank(message="用户名不能为空")
    private String userName;

    @NotBlank(message="邮箱不能为空")
    private String email;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
