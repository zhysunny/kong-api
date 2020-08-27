package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/8/27 0:40
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Services {
    private String next;
    private List<Service> data;
}
