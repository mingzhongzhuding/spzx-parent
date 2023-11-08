package com.pingchang.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/31  21:30
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {
    private List<String> noAuthUrls;
}
