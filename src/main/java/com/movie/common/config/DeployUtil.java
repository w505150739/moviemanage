package com.movie.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "deploy")
@PropertySource(value = "classpath:config/deploy.properties")
public class DeployUtil {

    private Long expiretime;

}
