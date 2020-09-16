package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/9/16 22:52
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Unhealthy {
    @JsonProperty("http_statuses")
    private List<Integer> httpStatuses;
    @JsonProperty("http_failures")
    private Integer httpFailures;
    @JsonProperty("tcp_failures")
    private Integer tcpFailures;
    private Integer timeouts;
    private Integer interval;
}
