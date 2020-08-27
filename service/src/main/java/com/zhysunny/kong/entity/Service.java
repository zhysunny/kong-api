package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/8/26 23:13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Service {
    private String id;
    private String name;
    private String protocol;
    private String host;
    private Integer port;
    private String path;
    private Integer retries;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("connect_timeout")
    private Long connectTimeout;
    @JsonProperty("write_timeout")
    private Long writeTimeout;
    @JsonProperty("read_timeout")
    private Long readTimeout;

    private List<String> tags;

    public static final Service DEFAULT = new Service() {{
        setRetries(5);
        setConnectTimeout(60000L);
        setWriteTimeout(60000L);
        setReadTimeout(60000L);
    }};

    public static final Service INIT = new Service() {{
        setProtocol("http");
        setHost("127.0.0.1");
        setPort(80);
        setPath("/");
        setRetries(5);
        setConnectTimeout(60000L);
        setWriteTimeout(60000L);
        setReadTimeout(60000L);
    }};
}
