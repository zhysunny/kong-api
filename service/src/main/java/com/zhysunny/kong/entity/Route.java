package com.zhysunny.kong.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhysunny
 * @date 2020/8/28 22:58
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Route {
    private String id;
    private String name;
    private List<String> protocols;
    private List<String> methods;
    private List<String> hosts;
    private List<String> paths;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("regex_priority")
    private Integer regexPriority;
    @JsonProperty("strip_path")
    private Boolean stripPath;
    @JsonProperty("preserve_host")
    private Boolean preserveHost;

    private List<String> tags;
    private List<String> snis;
    private List<String> sources;
    private List<String> destinations;
    private Service service;

    public static final Route DEFAULT = new Route() {{
        setRegexPriority(0);
        setStripPath(true);
        setPreserveHost(false);
        setProtocols(Arrays.asList("http", "https"));
    }};

    public static final Route INIT = new Route() {{
        setMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        setRegexPriority(0);
        setStripPath(false);
        setPreserveHost(false);
        setProtocols(Arrays.asList("http", "https"));
    }};
}
