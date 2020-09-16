package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/9/16 22:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Upstreams {
    private String next;
    private List<Upstream> data;
}
