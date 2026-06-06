package com.example.demo.workforce.io.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteRequest {
    private String siteName;
    private String location;
    private Boolean active;
}
