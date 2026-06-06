package com.example.demo.workforce.io.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClockInRequest {
    private Long workerId;
    private Long siteId;
}
