package com.amdocs.oss.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/ruleFrameworkFallback")
    public Mono<String> ruleFrameworkFallback(){
        return Mono.just("Rule-framework is down");
    }

    @RequestMapping("/feraFallback")
    public Mono<String> feraFallback(){
        return Mono.just("Fera is down");
    }
}
