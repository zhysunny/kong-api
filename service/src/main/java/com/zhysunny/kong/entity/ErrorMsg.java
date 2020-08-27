package com.zhysunny.kong.entity;

import lombok.Data;
import java.util.Map;

/**
 * @author zhysunny
 * @date 2020/8/27 0:02
 */
@Data
public class ErrorMsg {
    private String message;
    private String name;
    private Integer code;
    private Map<String, String> fields;
}
