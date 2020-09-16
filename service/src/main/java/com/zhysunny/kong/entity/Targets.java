package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/9/16 23:28
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Targets {
    private String next;
    private List<Target> data;
}
