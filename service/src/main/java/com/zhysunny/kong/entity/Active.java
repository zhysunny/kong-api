package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zhysunny
 * @date 2020/9/16 22:51
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Active {
    private Unhealthy unhealthy;
    private Healthy healthy;
    private String type;
    @JsonProperty("http_path")
    private String httpPath;
    @JsonProperty("https_sni")
    private String httpsSni;
    @JsonProperty("https_verify_certificate")
    private String httpsVerifyCertificate;
    private Integer timeout;
    private Integer concurrency;
}
