package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/9/16 22:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Upstream {
    private String id;
    private String name;

    @JsonProperty("hash_on")
    private String hashOn;
    @JsonProperty("hash_fallback_header")
    private String hashFallbackHeader;
    @JsonProperty("hash_on_cookie")
    private String hashOnCookie;
    @JsonProperty("hash_on_cookie_path")
    private String hashOnCookiePath;
    @JsonProperty("hash_fallback")
    private String hashFallback;
    @JsonProperty("hash_on_header")
    private String hashOnHeader;
    @JsonProperty("slots")
    private Integer slots;

    @JsonProperty("created_at")
    private Long createdAt;

    private List<String> tags;

    private HealthChecks healthchecks;

    public static final Upstream DEFAULT = new Upstream() {{
    }};

    public static final Upstream INIT = new Upstream() {{
    }};
}
