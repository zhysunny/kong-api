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
public class Healthy {
    private Integer successes;
    private Integer interval;
    @JsonProperty("http_statuses")
    private List<Integer> httpStatuses;
}
