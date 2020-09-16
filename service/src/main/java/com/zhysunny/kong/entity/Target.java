package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/9/16 23:29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Target {
    private String id;
    private String target;
    private Integer weight;
    @JsonProperty("created_at")
    private Long createdAt;

    private List<String> tags;

    private Upstream upstream;

    public static final Target DEFAULT = new Target() {{
        setWeight(100);
    }};

    public static final Target INIT = new Target() {{
        setWeight(100);
    }};
}
