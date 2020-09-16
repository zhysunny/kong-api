package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zhysunny
 * @date 2020/9/16 22:50
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HealthChecks {
    private Active active;
    private Passive passive;
}
