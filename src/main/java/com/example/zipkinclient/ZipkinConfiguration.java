package com.example.zipkinclient;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import lombok.extern.slf4j.Slf4j;

/**
 */
@Configuration
//@Slf4j
public class ZipkinConfiguration {

	// 20% selection only
	private static final double  QUALIFY_PERCENT = 20d/100; 

    @Bean
    Sampler customSampler() {
    	
    	System.out.println("============================================");
    	System.out.println("Configuring sampler.");
    	System.out.println("============================================");
    	
        return new Sampler() {
        	
            @Override
            public boolean isSampled(Span span) {

            	System.out.println("============================================");
            	System.out.println("isSampled():"+span.getName());
            	System.out.println("============================================");
            	
            	boolean isqualify = true;
                if (span.getName().toUpperCase().contains("KEYS")) {
                	double r = Math.random();
                	isqualify =  r <= QUALIFY_PERCENT;
                	System.out.println("============================================");
                	System.out.println("isSampled():"+QUALIFY_PERCENT+":"+r+":"+isqualify);
                	System.out.println("============================================");
                }
                if (span.getName().toUpperCase().contains("VALUES")) {
                	double r = 0;
                	isqualify = true;
                	System.out.println("============================================");
                	System.out.println("isSampled():"+QUALIFY_PERCENT+":"+r+":"+isqualify);
                	System.out.println("============================================");
                }
                System.out.println("customSampler.isSampled -> span.getName={} ::::isqualify={}" + span.getName() + ":"+ isqualify);
                
                return isqualify;
            }
        };
    }
}
