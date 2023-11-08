package com.pingchang.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/2  15:31
 */
@Data
@ConfigurationProperties(prefix = "spzx.minio")
public class MinioProperties {


    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
