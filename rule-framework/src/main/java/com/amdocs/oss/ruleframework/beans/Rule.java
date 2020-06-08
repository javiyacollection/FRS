package com.amdocs.oss.ruleframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RULES_TB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    @Id
    private String rule;
    private String errorCode;
    private String actionUrl;
    private boolean responseRequired;
}
