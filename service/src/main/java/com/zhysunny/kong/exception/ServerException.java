package com.zhysunny.kong.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhysunny
 * @date 2020/8/27 0:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerException {
    private Integer code;
    private String message;
}
