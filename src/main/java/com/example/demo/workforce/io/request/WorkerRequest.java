package com.example.demo.workforce.io.request;
import com.example.demo.workforce.enums.Designation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WorkerRequest {
    private String name;
    private String phone;
    private Designation designation;
    private BigDecimal dailyWageRate;
    private Boolean active;
}
