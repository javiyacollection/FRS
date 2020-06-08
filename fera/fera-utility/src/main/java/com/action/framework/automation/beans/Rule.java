package com.action.framework.automation.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    private String rule;
    private String errorCode;
    private String actionUrl;
    private boolean responseRequired;
}
