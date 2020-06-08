package com.amdocs.oss.ruleframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEvent {

    private Order order;
    private Rule rule;
    private String errorMessage;
    private String errorCode;
    private String status;
}
