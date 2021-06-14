package com.dailycodebuffer.springboottutorial.config;

import javassist.util.HotSwapAgent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id="features")
public class FeatureEndPoint {

    private static final Map<String,Feature> featureMap =
            new ConcurrentHashMap<>();

    public FeatureEndPoint(){
        featureMap.put("department", new Feature(true));
        featureMap.put("user", new Feature(false));
        featureMap.put("authentication", new Feature( false));
    }

    @ReadOperation
    public Map<String, Feature> features(){
        return featureMap;
    }

    @ReadOperation
    public  Feature feature(@Selector String featureName){
        return featureMap.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature{
        private boolean enabled;

    }
}
